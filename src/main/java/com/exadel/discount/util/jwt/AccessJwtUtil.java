package com.exadel.discount.util.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AccessJwtUtil extends JwtUtil {
    @Value("${jwt.access.expiration}")
    private long ACCESS_TOKEN_EXPIRATION_TIME;

    @Override
    protected String getRole(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> packedAuthorities = userDetails.getAuthorities();

        String authorities = "";
        for (GrantedAuthority authority : packedAuthorities)
            authorities = authorities + " " + authority.getAuthority();
        authorities = StringUtils.strip(authorities);

        return authorities;
    }

    @Override
    protected long getTokenDuration() {
        return ACCESS_TOKEN_EXPIRATION_TIME;
    }
}
