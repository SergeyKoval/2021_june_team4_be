package com.exadel.discount.model.token;

import com.exadel.discount.model.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenPayload {
    private final String email;
    private final Role role;
}
