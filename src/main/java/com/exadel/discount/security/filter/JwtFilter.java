package com.exadel.discount.security.filter;

import com.exadel.discount.service.JwtService;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

@Component
@Setter
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final String AUTHORIZATION_HEADER_TYPE = "Bearer ";

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (SecurityContextHolder.getContext().getAuthentication() == null
                && startsWith(authorizationHeader, AUTHORIZATION_HEADER_TYPE)) {
            final String token = substringAfter(authorizationHeader, " ");
            final String givenUsername = jwtService.getSubject(token);
            final String givenRole = jwtService.getRole(token);

            if (isNoneEmpty(givenUsername)) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(givenUsername);

                if (jwtService.isTokenRefreshOne(givenRole)) {
                    AbstractAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails, null,
                            Collections.singletonList(new SimpleGrantedAuthority(givenRole)));
                    setAuthentication(authentication, request);
                } else {
                    AbstractAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());
                    setAuthentication(authentication, request);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(AbstractAuthenticationToken authentication, HttpServletRequest request) {
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
