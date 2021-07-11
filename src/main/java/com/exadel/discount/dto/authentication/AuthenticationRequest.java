package com.exadel.discount.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class is intended as an input parameter for login in
 * {@link com.exadel.discount.controller.AuthenticationController#login(AuthenticationRequest)}.
 * It has a username and password that must be filled from the client side.
 */

@Getter
@AllArgsConstructor
public class AuthenticationRequest {
    private final String username;
    private final String password;
}
