package com.exadel.discount.model.dto.authentication;

import lombok.Builder;
import lombok.Getter;

/**
 * This class is intended as an output value for the client side in
 * {@link com.exadel.discount.controller.AuthenticationController#login(AuthenticationRequest)}.
 * It has access and refresh JWTs for the security logic of the server.
 */

@Builder
@Getter
public class AuthenticationResponse {
    private final String accessToken;
    private final String refreshToken;
}
