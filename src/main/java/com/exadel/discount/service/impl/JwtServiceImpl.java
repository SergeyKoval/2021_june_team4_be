package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.exception.InvalidTokenException;
import com.exadel.discount.service.JwtGenerationService;
import com.exadel.discount.service.JwtService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * This class represent a service for JWTs.
 */

@Service
@Setter
@Slf4j
public class JwtServiceImpl implements JwtService, JwtGenerationService {
    private final String ROLES_CLAIM_NAME = "role";
    public final String REFRESH_ROLE = "ROLE_REFRESH";

    @Value("${jwt.token.expiration.seconds.access}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.token.expiration.seconds.refresh}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;
    @Value("${jwt.token.encryption.key}")
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
    public String generateAccessToken(UserDetails userDetails) {
        log.debug("preparing an access token creation data");
        return buildToken(
                userDetails.getUsername(),
                ACCESS_TOKEN_EXPIRATION_TIME,
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElseThrow(() -> new InvalidTokenException("User does not have any role")));
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        log.debug("preparing a refresh token creation data");
        return buildToken(userDetails.getUsername(), REFRESH_TOKEN_EXPIRATION_TIME, REFRESH_ROLE);
    }

    /**
     * This method builds JWT based on a prepared data that are given by
     * {@link #generateAccessToken(UserDetails)} and {@link #generateRefreshToken(UserDetails)}
     *
     * @param subject        it will be set in a subject claim.
     * @param expirationTime it will be set in a expiration time claim.
     * @param role           it will be set in a role claim.
     * @return a built JWT.
     */
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
