package com.exadel.discount.util.jwt;

import com.exadel.discount.config.JwtVariablesConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

import static com.exadel.discount.config.JwtVariablesConfig.REFRESH_TOKEN_EXPIRATION_TIME;
import static com.exadel.discount.config.JwtVariablesConfig.REFRESH_ROLE;

@Service("refreshJwtUtil")
public class RefreshJwtUtil extends JwtUtil{
    @Override
    protected Collection<? extends GrantedAuthority> initRoleClaim(UserDetails userDetails) {
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(JwtVariablesConfig.getProperty(REFRESH_ROLE)));
        return set;
    }

    @Override
    protected long initExpirationTimeClaim() {
        return Long.parseLong(JwtVariablesConfig.getProperty(REFRESH_TOKEN_EXPIRATION_TIME));
    }

    public static Collection<? extends GrantedAuthority> getRefreshAuthority() {
        HashSet<SimpleGrantedAuthority> authority = new HashSet<>();
        authority.add(new SimpleGrantedAuthority(JwtVariablesConfig.getProperty(REFRESH_ROLE)));
        return authority;
    }
}
