package com.exadel.discount.repository;

import com.exadel.discount.model.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String email;
    private String password;
    private Role role;
}
