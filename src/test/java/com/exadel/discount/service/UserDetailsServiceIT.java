package com.exadel.discount.service;

import com.exadel.discount.controller.AbstractIT;
import com.exadel.discount.security.entity.UserDetailsImpl;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.junit.Assert.assertThrows;

@ActiveProfiles("test")
@Sql("classpath:testdata/userdetails_test_data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserDetailsServiceIT extends AbstractIT {
    private static final UUID ID = UUID.fromString("3633f3cf-7208-4d67-923d-ce6b2cec29e3");
    private static final String EMAIL = "adminov@mail.com";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    private UserDetailsImpl expectedUserDetails;

    @BeforeEach
    public void setUp() {
        expectedUserDetails = UserDetailsImpl
                .builder()
                .id(ID)
                .username(EMAIL)
                .password("admin")
                .roles("ADMIN")
                .build();
    }

    @Test
    public void testLoadUserByUsername() {

        UserDetails actual = userDetailsService.loadUserByUsername(EMAIL);

        Assertions.assertEquals(actual.getUsername(), expectedUserDetails.getUsername());
        Assertions.assertEquals(actual.getAuthorities(), expectedUserDetails.getAuthorities());
        Assertions.assertEquals(actual.isAccountNonExpired(), expectedUserDetails.isAccountNonExpired());
        Assertions.assertTrue(passwordEncoder.matches("admin", actual.getPassword()));
    }

    @Test
    public void testExceptionDeleteById() {
        Exception exception = assertThrows(BadCredentialsException.class,
                () -> userDetailsService.loadUserByUsername("wrongEmail@gmail.com"));
    }
}
