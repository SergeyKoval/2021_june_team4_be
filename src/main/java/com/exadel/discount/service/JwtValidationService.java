package com.exadel.discount.service;

public interface JwtValidationService {
    boolean isTokenAccessOne(String givenRole);

    boolean isTokenRefreshOne(String givenRole);

    boolean isTokenExpired(String token);

    boolean validateToken(String token);
}
