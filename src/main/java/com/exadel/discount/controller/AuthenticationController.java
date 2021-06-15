package com.exadel.discount.controller;

import com.exadel.discount.model.http.AuthenticationRequest;
import com.exadel.discount.model.http.AuthenticationResponse;
import com.exadel.discount.model.http.RefreshRequest;
import com.exadel.discount.model.http.RefreshResponse;
import com.exadel.discount.model.token.TokenPayload;
import com.exadel.discount.service.UserDetailsServiceImpl;
import com.exadel.discount.util.AccessTokenUtil;
import com.exadel.discount.util.RefreshTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private AccessTokenUtil accessTokenUtil;
    private RefreshTokenUtil refreshTokenUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    UserDetailsServiceImpl userDetailsService,
                                    AccessTokenUtil accessTokenUtil,
                                    RefreshTokenUtil refreshTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.accessTokenUtil = accessTokenUtil;
        this.refreshTokenUtil = refreshTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        final TokenPayload payload = userDetailsService.loadUserRoleByUsername(request.getUsername());
        final String accessToken = accessTokenUtil.generateToken(payload);
        final String refreshToken = refreshTokenUtil.generateToken(payload);
        return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) throws Exception {
        String refreshToken = request.getRefreshToken();
        String username = refreshTokenUtil.extractUsername(refreshToken);

        if (username != null && refreshToken != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (refreshTokenUtil.validateToken(refreshToken, userDetails)) {
                final TokenPayload payload = userDetailsService.loadUserRoleByUsername(username);
                final String newAccessToken = accessTokenUtil.generateToken(payload);
                final String newRefreshToken = refreshTokenUtil.generateToken(payload);
                return ResponseEntity.ok(new RefreshResponse(newAccessToken, newRefreshToken));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
