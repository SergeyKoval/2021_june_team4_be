package com.exadel.discount.filters;

import com.exadel.discount.service.UserDetailsServiceImpl;
import com.exadel.discount.util.jwt.JwtUtil;
import com.exadel.discount.util.jwt.RefreshJwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

@Component
@Setter
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final String BEARER_TYPE_OF_AUTHORIZATION_HEADER = "Bearer ";

    @Value("${jwt.refresh.role}")
    private String REFRESH_ROLE;

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        AbstractAuthenticationToken authentication = null;

        if (isNotAuthorized() && isAuthorizationHeaderSuitable(authorizationHeader)) {
            final String token = StringUtils.substring(authorizationHeader, 7);
            final String username = JwtUtil.extractUsername(token);
            String role = JwtUtil.extractRoles(token);

            if (isSearchKeyForStorageValid(username)) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (StringUtils.equals(role, REFRESH_ROLE)) {
                    if (JwtUtil.validateToken(token, userDetails)) {
                        authentication = new PreAuthenticatedAuthenticationToken(
                                userDetails, null, RefreshJwtUtil.getRefreshAuthority());
                    }
                } else if (JwtUtil.validateToken(token, userDetails)) {
                    authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                }
                setSecurityContextAuthentication(request, authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isNotAuthorized() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean isAuthorizationHeaderSuitable(String authorizationHeader) {
        return StringUtils.startsWith(authorizationHeader, BEARER_TYPE_OF_AUTHORIZATION_HEADER);
    }

    private boolean isSearchKeyForStorageValid(String key) {
        return StringUtils.isNoneEmpty(key);
    }

    private void setSecurityContextAuthentication(HttpServletRequest request, AbstractAuthenticationToken authentication) {
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
