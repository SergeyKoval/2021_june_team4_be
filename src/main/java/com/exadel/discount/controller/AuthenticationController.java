package com.exadel.discount.controller;

import com.exadel.discount.dto.authentication.RefreshResponse;
import com.exadel.discount.security.annotation.RefreshAccess;
import com.exadel.discount.dto.authentication.AuthenticationRequest;
import com.exadel.discount.dto.authentication.AuthenticationResponse;
import com.exadel.discount.service.JwtGenerationService;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        return AuthenticationResponse.builder()
                .accessToken(jwtGenerationService.generateAccessToken(userDetails))
                .refreshToken(jwtGenerationService.generateRefreshToken(userDetails))
                .build();
    }

    @RefreshAccess
    @PostMapping("/refresh")
    public RefreshResponse refreshToken() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        return RefreshResponse.builder()
                .accessToken(jwtGenerationService.generateAccessToken(userDetails))
                .build();
    }
}
