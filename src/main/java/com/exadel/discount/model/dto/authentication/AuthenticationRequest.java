package com.exadel.discount.model.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

/**
 * This class is intended as an input parameter for login in
 * {@link com.exadel.discount.controller.AuthenticationController#login(AuthenticationRequest)}.
 * It has a username and password that must be filled from the client side.
 */

@Getter
@AllArgsConstructor
public class AuthenticationRequest {
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
}
