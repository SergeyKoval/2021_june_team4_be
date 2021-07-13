package com.exadel.discount.service.impl;

import com.exadel.discount.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        log.debug("getting a user data from a database");
        com.exadel.discount.model.entity.User user = repository
                .findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(String.format("User with email %s not found", email)));

        log.debug("creating a security user with a given user data");
        return User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
