package com.exadel.discount.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class UserDetailsImpl implements UserDetails {
    private String id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    private UserDetailsImpl(String id, String username, String password,
                            Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public static UserDetailsImplBuilder builder() {
        return new UserDetailsImplBuilder();
    }

    public String getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class UserDetailsImplBuilder {
        private String id;
        private String email;
        private String password;
        private Collection<? extends GrantedAuthority> grantedAuthorities;

        UserDetailsImplBuilder() {
        }

        public UserDetailsImplBuilder id(UUID id) {
            this.id = id.toString();
            return this;
        }

        public UserDetailsImplBuilder username(String email) {
            this.email = email;
            return this;
        }

        public UserDetailsImplBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDetailsImplBuilder roles(String roleName) {
            this.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName));
            return this;
        }

        public UserDetailsImpl build() {
            return new UserDetailsImpl(id, email, password, grantedAuthorities);
        }
    }
}
