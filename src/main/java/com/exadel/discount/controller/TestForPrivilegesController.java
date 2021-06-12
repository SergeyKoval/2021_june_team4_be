package com.exadel.discount.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/privilege")
public class TestForPrivilegesController {

    @GetMapping("/ordinary")
    @PreAuthorize("hasAuthority('vendors:allUsers')")
    public String getOrdinaryResource() {
        return "Hello, you have an access to ordinary resources";
    }

    @GetMapping("privileged")
    @PreAuthorize("hasAuthority('vendors:privilegedUsers')")
    public String getPrivilegedResource() {
        return "Hello, you have an access to privileged resources";
    }
}
