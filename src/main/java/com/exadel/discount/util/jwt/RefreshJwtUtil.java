package com.exadel.discount.util.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class RefreshJwtUtil extends JwtUtil{
    @Value("${jwt.refresh.expiration}")
    private long REFRESH_TOKEN_EXPIRATION_TIME;
    private static String REFRESH_ROLE;

    @Value("${jwt.refresh.role}")
    public void setRefreshRole(String refreshRole) {
        RefreshJwtUtil.REFRESH_ROLE = refreshRole;
    }

    @Override
    protected String getRole(UserDetails userDetails) {
        return REFRESH_ROLE;
    }

    @Override
    protected long getTokenDuration() {
        return REFRESH_TOKEN_EXPIRATION_TIME;
    }

    public static Collection<? extends GrantedAuthority> getRefreshAuthority() {
        List<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(REFRESH_ROLE));
        return authority;
    }
}
