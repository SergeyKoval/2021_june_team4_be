package com.exadel.discount.util.jwt;

import com.exadel.discount.config.JwtVariablesConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static com.exadel.discount.config.JwtVariablesConfig.ACCESS_TOKEN_EXPIRATION_TIME;

@Service
public class AccessJwtUtil extends JwtUtil {
    @Override
    protected Collection<? extends GrantedAuthority> initRoleClaim(UserDetails userDetails) {
        return userDetails.getAuthorities();
    }

    @Override
    protected long initExpirationTimeClaim() {
        return Long.parseLong(JwtVariablesConfig.getProperty(ACCESS_TOKEN_EXPIRATION_TIME));
    }
}
