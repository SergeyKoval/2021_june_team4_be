package com.exadel.discount.controller;

import com.exadel.discount.model.dto.authentication.RefreshResponse;
import com.exadel.discount.exception.InvalidTokenException;
import com.exadel.discount.security.annotation.RefreshAccess;
import com.exadel.discount.model.dto.authentication.AuthenticationRequest;
import com.exadel.discount.model.dto.authentication.AuthenticationResponse;
import com.exadel.discount.service.JwtGenerationService;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * This class is intended for user authentication based on JWTs.
 */

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtGenerationService jwtGenerationService;

    /**
     * This method receives a username and password and validates them in a database.
     *
     * @param request a username and password of a user stored in a database.
     * @return constructed access and refresh JWTs based on a user data.
     * @throws InternalAuthenticationServiceException if given username or password are incorrect.
     * @throws InvalidTokenException                  if a user does not have any role in a database.
     */
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest request) {
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

    /**
     * This method receives a refresh JWT and then constructs and gives a new access JWT.
     *
     * @return a constructed access token based on a user data.
     * @throws InternalAuthenticationServiceException if given username or password are incorrect.
     * @throws AccessDeniedException                  if given JWT has no rights for this endpoint.
     * @throws InvalidTokenException                  if a user does not have any role in a database.
     */
    @RefreshAccess
    @PostMapping("/refresh")
    public RefreshResponse refreshAccessJWT() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

        return RefreshResponse.builder()
                .accessToken(jwtGenerationService.generateAccessToken(userDetails))
                .build();
    }
}
