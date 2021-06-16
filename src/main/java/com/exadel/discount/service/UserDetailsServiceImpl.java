package com.exadel.discount.service;

import com.exadel.discount.model.token.TokenPayload;
import com.exadel.discount.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.exadel.discount.entity.User user = repository.findByEmail(email);
        return new User(
                user.getEmail(),
                user.getPassword(),
                user.getRole().getAuthorities()
        );
    }

    public TokenPayload loadUserRoleByUsername(String email) throws UsernameNotFoundException {
        com.exadel.discount.entity.User user = repository.findByEmail(email);
        return new TokenPayload(
                user.getEmail(),
                user.getRole()
        );
    }
}
