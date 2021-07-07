package com.exadel.discount.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class AuthenticationRequest {
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
}
