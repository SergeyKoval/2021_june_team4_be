package com.exadel.discount.service;

import java.util.Date;

public interface JwtService {
    String getSubject(String token);

    Date getExpiration(String token);

    String getRole(String token);

    boolean isTokenAccessOne(String givenRole);

    boolean isTokenRefreshOne(String givenRole);

    boolean isTokenExpired(String token);

    boolean validateToken(String token);
}
