package com.exadel.discount.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Permission {
    VENDORS_ORDINARY_ACCESS("vendors:allUsers"),
    VENDORS_PRIVILEGED_ACCESS("vendors:privilegedUsers");

    private final String permission;
}
