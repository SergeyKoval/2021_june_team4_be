package com.exadel.discount.service;

public interface JwtService {
    String getSubject(String token);

    String getRole(String token);
}
