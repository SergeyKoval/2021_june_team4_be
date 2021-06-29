package com.exadel.discount.service;

import java.util.Date;

public interface JwtExtractionService {
    String getSubject(String token);

    Date getExpiration(String token);

    String getRole(String token);
}
