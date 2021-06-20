package com.exadel.discount.filters;

import com.exadel.discount.config.JwtVariablesConfig;
import com.exadel.discount.service.UserDetailsServiceImpl;
import com.exadel.discount.util.jwt.JwtUtil;
import com.exadel.discount.util.jwt.RefreshJwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import static com.exadel.discount.config.JwtVariablesConfig.REFRESH_ROLE;

@Component
@Setter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessTokenRequestFilter extends OncePerRequestFilter {
    String AUTHORIZATION_HEADER = "Authorization";
    String BEARER_TYPE_OF_AUTHORIZATION_HEADER = "Bearer ";

    UserDetailsServiceImpl userDetailsService;

    @NonFinal
    AbstractAuthenticationToken authentication;
    @NonFinal
    String token;
    @NonFinal
    String username;
    @NonFinal
    String role;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (isNotAuthorized() && hasSuitableAuthorizationHeader(authorizationHeader)) {
            extractPayload(authorizationHeader);

            if (hasValidSearchKeyForStorage()) {
                UserDetails userDetails = loadUserDataFromStorageThroughSearchKey();

                if (StringUtils.equals(role, JwtVariablesConfig.getProperty(REFRESH_ROLE))) {
                    if (JwtUtil.validateToken(token, userDetails)) {
                        this.authentication = new PreAuthenticatedAuthenticationToken(
                                userDetails, null, RefreshJwtUtil.getRefreshAuthority());
                    }
                } else {
                    if (JwtUtil.validateToken(token, userDetails)) {
                        this.authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                    }
                }
                setSecurityContextAuthentication(request, authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isNotAuthorized() {
        return SecurityContextHolder.getContext().getAuthentication() == null;
    }

    private boolean hasSuitableAuthorizationHeader(String authorizationHeader) {
        return
                StringUtils.isNoneEmpty(authorizationHeader) &&
                        StringUtils.startsWith(authorizationHeader, BEARER_TYPE_OF_AUTHORIZATION_HEADER);
    }

    private UserDetails loadUserDataFromStorageThroughSearchKey() {
        return this.userDetailsService.loadUserByUsername(username);
    }

    private boolean hasValidSearchKeyForStorage() {
        return StringUtils.isNoneEmpty(username);
    }

    private void setSecurityContextAuthentication(HttpServletRequest request, AbstractAuthenticationToken authentication) {
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void extractPayload(String authorizationHeader) {
        setToken(authorizationHeader.substring(7));
        setUsername(JwtUtil.extractUsername(token));
        setRole(JwtUtil.extractRole(token));
        extractRoleKeyWordFromClaim();
    }

    private void extractRoleKeyWordFromClaim() {
        final String preSyntax = "[{authority=";
        final String postSyntax = "}]";
        setRole(StringUtils.stripStart(role, preSyntax));
        setRole(StringUtils.stripEnd(role, postSyntax));
    }
}
