package com.exadel.discount.controller;

import com.exadel.discount.annotation.AdminAccess;
import com.exadel.discount.annotation.RefreshAccess;
import com.exadel.discount.annotation.UserAccess;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {

    @GetMapping("/a")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @AdminAccess
    public String getAdmin() {
        return "Hello admin";
    }

    @GetMapping("/r")
//    @PreAuthorize("hasRole('ROLE_REFRESH')")
    @RefreshAccess
    public String getRefresh() {
        return "Hello refresh";
    }

    @GetMapping("/u")
//    @PreAuthorize("hasRole('ROLE_USER')")
    @UserAccess
    public String getUser() {
        return "Hello user";
    }
}
