package com.exadel.discount.controller;

import com.exadel.discount.annotation.RefreshAccess;
import com.exadel.discount.dto.authentication.AuthenticationRequest;
import com.exadel.discount.dto.authentication.AuthenticationResponse;
import com.exadel.discount.service.JwtGenerationService;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtGenerationService jwtGenerationService;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String accessToken = jwtGenerationService.generateAccessToken(userDetails);
        final String refreshToken = jwtGenerationService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(accessToken, refreshToken));
    }

    @CrossOrigin
    @RefreshAccess
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        final String newAccessToken = jwtGenerationService.generateAccessToken(userDetails);

        return ResponseEntity.ok(newAccessToken);
    }
}
