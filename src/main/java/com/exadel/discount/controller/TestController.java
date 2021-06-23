package com.exadel.discount.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private static String yml;

    @Value("${jwt.encryption.key}")
    public void setYml(String yml) {
        TestController.yml = yml;
    }

    @GetMapping("/a")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdmin() {
        return "Hello admin";
    }

    @GetMapping("/r")
    @PreAuthorize("hasRole('ROLE_REFRESH')")
    public String getRefresh() {
        return "Hello refresh";
    }

    @GetMapping("/u")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUser() {
        return "Hello user";
    }

    @GetMapping("/yml")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getYml() {
        return yml;
    }
}
