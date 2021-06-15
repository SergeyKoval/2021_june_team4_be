package com.exadel.discount.service;

import com.exadel.discount.model.token.TokenPayload;
import com.exadel.discount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.exadel.discount.model.security.User> user = repository.findByEmail(email);
        return new User(
                user.get().getEmail(),
                user.get().getPassword(),
                user.get().getRole().getAuthorities()
        );
    }

    public TokenPayload loadUserRoleByUsername(String email) throws UsernameNotFoundException {
        Optional<com.exadel.discount.model.security.User> user = repository.findByEmail(email);
        return new TokenPayload(
                user.get().getEmail(),
                user.get().getRole()
        );
    }
}
