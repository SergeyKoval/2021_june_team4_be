package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.service.JwtGenerationService;
import com.exadel.discount.service.JwtService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@Setter
@Getter
@Slf4j
public class JwtServiceImpl implements JwtService, JwtGenerationService {
    private final String ROLES_CLAIM_NAME = "role";
    private final String REFRESH_ROLE = "ROLE_REFRESH";

    @Value("${jwt.access.expiration.seconds}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.refresh.expiration.seconds}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.encryption.key}")
    private String TOKEN_ENCRYPTION_KEY;

    @Override
    public String getSubject(String token) {
        log.debug("getting a subject from a token");
        return JWT.require(Algorithm.HMAC256(TOKEN_ENCRYPTION_KEY))
                .build().verify(token)
                .getSubject();
    }

    @Override
    public String getRole(String token) {
        log.debug("getting a role from a token");
        return JWT.require(Algorithm.HMAC256(TOKEN_ENCRYPTION_KEY))
                .build().verify(token)
                .getClaim(ROLES_CLAIM_NAME)
                .asString();
    }

    @Override
    public boolean isTokenRefreshOne(String givenRole) {
        log.debug("checking a token for the refresh affiliation");
        return StringUtils.equals(givenRole, REFRESH_ROLE);
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        log.debug("preparing an access token creation data");
        return buildToken(
                userDetails.getUsername(),
                ACCESS_TOKEN_EXPIRATION_TIME,
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" ")));
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        log.debug("preparing a refresh token creation data");
        return buildToken(userDetails.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME, REFRESH_ROLE);
    }

    private String buildToken(String subject, long expirationTime, String role) {
        log.debug("creating a token");
        Instant currentTime = Instant.now();
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(Date.from(currentTime))
                .withExpiresAt(Date.from(currentTime.plusSeconds(expirationTime)))
                .withClaim(ROLES_CLAIM_NAME, role)
                .sign(Algorithm.HMAC256(TOKEN_ENCRYPTION_KEY));
    }
}
