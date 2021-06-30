package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.entity.Role;
import com.exadel.discount.service.JwtService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.contains;

@Service
@Setter
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final String ROLES_CLAIM_NAME = "role";

    @Value("${jwt.encryption.key}")
    private String TOKEN_ENCRYPTION_KEY;
    @Value("${jwt.refresh.role}")
    private String REFRESH_ROLE;

    @Override
    public String getSubject(String token) {
        log.debug("getting a subject from a token");
        return JWT.require(Algorithm.HMAC256(TOKEN_ENCRYPTION_KEY))
                .build().verify(token)
                .getSubject();
    }

    @Override
    public Date getExpiration(String token) {
        log.debug("getting an expiration from a token");
        return JWT.require(Algorithm.HMAC256(TOKEN_ENCRYPTION_KEY))
                .build().verify(token)
                .getExpiresAt();
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
    public boolean isTokenAccessOne(String givenRole) {
        log.debug("checking a token for the access affiliation");
        return Stream.of(Role.USER, Role.ADMIN)
                .map(String::valueOf)
                .anyMatch(role -> contains(givenRole, role));
    }

    @Override
    public boolean isTokenRefreshOne(String givenRole) {
        log.debug("checking a token for the refresh affiliation");
        return contains(givenRole, REFRESH_ROLE);
    }

    @Override
    public boolean isTokenExpired(String token) {
        log.debug("checking for token expiration time");
        return getExpiration(token).toInstant().isBefore(Instant.now());
    }

    @Override
    public boolean validateToken(String token) {
        log.debug("validating a token");
        return (!isTokenExpired(token));
    }
}
