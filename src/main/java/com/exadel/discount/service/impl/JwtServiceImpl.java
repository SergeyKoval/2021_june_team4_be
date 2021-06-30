package com.exadel.discount.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exadel.discount.service.JwtService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Setter
@Getter
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final String ROLES_CLAIM_NAME = "role";
    private final String TOKEN_ENCRYPTION_KEY = "encryptionSecret";
    private final String REFRESH_ROLE = "ROLE_REFRESH";

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
        System.out.println(givenRole + " " +REFRESH_ROLE);
        System.out.println(StringUtils.equals(givenRole, REFRESH_ROLE));
        return StringUtils.equals(givenRole, REFRESH_ROLE);
    }
}
