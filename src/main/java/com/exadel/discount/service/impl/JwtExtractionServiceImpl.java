package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.service.JwtExtractionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Setter
@Slf4j
public class JwtExtractionServiceImpl implements JwtExtractionService {
    private final String ROLES_CLAIM_NAME = "role";

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
}
