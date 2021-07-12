package com.exadel.discount.model.dto.authentication;

import lombok.Builder;
import lombok.Getter;

/**
 * This class is intended as an output value for the client side in
 * {@link com.exadel.discount.controller.AuthenticationController#refreshAccessJWT()}.
 * It has an access JWT for the security logic of the server.
 */

@Builder
@Getter
public class RefreshResponse {
    private final String accessToken;
}
