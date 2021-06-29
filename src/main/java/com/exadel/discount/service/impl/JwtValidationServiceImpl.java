package com.exadel.discount.service.impl;

import com.exadel.discount.entity.Role;
import com.exadel.discount.service.JwtExtractionService;
import com.exadel.discount.service.JwtValidationService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.contains;

@Service
@Setter
@RequiredArgsConstructor
public class JwtValidationServiceImpl implements JwtValidationService {
    @Value("${jwt.refresh.role}")
    private String REFRESH_ROLE;

    private final JwtExtractionService extractionService;

    @Override
    public boolean isTokenAccessOne(String givenRole) {
        return Stream.of(Role.USER, Role.ADMIN)
                .map(String::valueOf)
                .anyMatch(role -> contains(givenRole, role));
    }

    @Override
    public boolean isTokenRefreshOne(String givenRole) {
        return contains(givenRole, REFRESH_ROLE);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractionService.getExpiration(token).toInstant().isBefore(Instant.now());
    }

    @Override
    public boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }
}