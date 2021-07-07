package com.exadel.discount.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtGenerationService {
    String generateAccessToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);
}
