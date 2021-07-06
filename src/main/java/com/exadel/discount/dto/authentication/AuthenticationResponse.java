package com.exadel.discount.dto.authentication;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AuthenticationResponse {
    private final String accessToken;
    private final String refreshToken;
}
