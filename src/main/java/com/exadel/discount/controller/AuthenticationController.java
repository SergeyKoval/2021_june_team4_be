package com.exadel.discount.controller;

import com.exadel.discount.model.http.AuthenticationRequest;
import com.exadel.discount.model.http.AuthenticationResponse;
import com.exadel.discount.service.UserDetailsServiceImpl;
import com.exadel.discount.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil accessJwtUtil;
    private final JwtUtil refreshJwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));
        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Incorrect email or password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String accessToken = accessJwtUtil.generateToken(userDetails);
        final String refreshToken = refreshJwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
    }

    @PostMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_REFRESH')")
    public ResponseEntity<?> refreshToken() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (StringUtils.isNoneEmpty(username)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            final String newAccessToken = accessJwtUtil.generateToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(newAccessToken);
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your session was ended. Please, log in.");
    }
}
