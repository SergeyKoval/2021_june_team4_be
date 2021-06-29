package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.service.JwtGenerationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Setter
public class JwtGenerationServiceImpl implements JwtGenerationService {
    private final String ROLES_CLAIM_NAME = "role";

    @Value("${jwt.encryption.key}")
    private String TOKEN_ENCRYPTION_KEY;
    @Value("${jwt.refresh.role}")
    private String REFRESH_ROLE;
    @Value("${jwt.refresh.expiration}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.access.expiration}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(
                userDetails.getUsername(),
                ACCESS_TOKEN_EXPIRATION_TIME,
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining()));
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME, REFRESH_ROLE);
    }

    private String buildToken(String subject, long expirationTime, String role) {
        Instant currentTime = Instant.now();
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(Date.from(currentTime))
                .withExpiresAt(Date.from(currentTime.plusSeconds(expirationTime)))
                .withClaim(ROLES_CLAIM_NAME, role)
                .sign(Algorithm.HMAC256(TOKEN_ENCRYPTION_KEY));
    }
}