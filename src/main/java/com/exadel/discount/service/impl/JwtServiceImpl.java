package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.exception.InvalidTokenException;
import com.exadel.discount.security.entity.UserDetailsImpl;
import com.exadel.discount.service.JwtGenerationService;
import com.exadel.discount.service.JwtService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represent a service for JWTs.
 */

@Service
@ConfigurationProperties(prefix = "jwt.encryption")
@Setter
@Slf4j
public class JwtServiceImpl implements JwtService, JwtGenerationService {
    private final String ROLES_CLAIM_NAME = "role";
    private final String ID_CLAIM_NAME = "id";
    public final String REFRESH_ROLE = "ROLE_REFRESH";
    @Value("${jwt.expiration.access}")
    private long ACCESS_EXPIRATION;
    @Value("${jwt.expiration.refresh}")
    private long REFRESH_EXPIRATION;
    private String ENCRYPTION_KEY;

    @Override
    public String getSubject(String token) {
        log.debug("getting a subject from a token");
        return JWT.require(Algorithm.HMAC256(ENCRYPTION_KEY))
                .build().verify(token)
                .getSubject();
    }

    @Override
    public String getRole(String token) {
        log.debug("getting a role from a token");
        return JWT.require(Algorithm.HMAC256(ENCRYPTION_KEY))
                .build().verify(token)
                .getClaim(ROLES_CLAIM_NAME)
                .asString();
    }

    @Override
    public String generateAccessToken(UserDetails userDetails) {
        log.debug("preparing an access token creation data");
        Map<String, String> claims = new HashMap<>();
        String role = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow(() -> new InvalidTokenException("User does not have any role"));
        claims.put(ROLES_CLAIM_NAME, role);
        if (userDetails instanceof UserDetailsImpl) {
            claims.put(ID_CLAIM_NAME, ((UserDetailsImpl) userDetails).getId());
        }
        return buildToken(userDetails.getUsername(), ACCESS_EXPIRATION, claims);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        log.debug("preparing a refresh token creation data");
        Map<String, String> claims = new HashMap<>();
        claims.put(ROLES_CLAIM_NAME, REFRESH_ROLE);
        if (userDetails instanceof UserDetailsImpl) {
            claims.put(ID_CLAIM_NAME, ((UserDetailsImpl) userDetails).getId());
        }
        return buildToken(userDetails.getUsername(), REFRESH_EXPIRATION, claims);
    }

    /**
     * This method builds JWT based on a prepared data that are given by
     * {@link #generateAccessToken(UserDetails)} and {@link #generateRefreshToken(UserDetails)}
     *
     * @param subject        it will be set in a subject claim.
     * @param expirationTime it will be set in a expiration time claim.
     * @return a built JWT.
     */
    private String buildToken(String subject, long expirationTime, Map<String, String> claims) {
        log.debug("creating a token");
        Instant currentTime = Instant.now();
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(Date.from(currentTime))
                .withExpiresAt(Date.from(currentTime.plusSeconds(expirationTime)))
                .withPayload(claims)
                .sign(Algorithm.HMAC256(ENCRYPTION_KEY));
    }
}
