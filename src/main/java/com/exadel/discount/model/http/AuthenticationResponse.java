package com.exadel.discount.model.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String accessToken;
    private final String refreshToken;
}
