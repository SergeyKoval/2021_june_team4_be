package com.exadel.discount.filters;

import com.exadel.discount.service.JwtExtractionService;
import com.exadel.discount.service.JwtValidationService;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substring;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

@Component
@Setter
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtExtractionService extractionService;
    private final JwtValidationService validationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String AUTHORIZATION_HEADER_TYPE = "Bearer ";

        if (SecurityContextHolder.getContext().getAuthentication() == null
                && startsWith(authorizationHeader, AUTHORIZATION_HEADER_TYPE)) {
            final String token = substring(authorizationHeader, 7);

            if (validationService.validateToken(token)) {
                final String givenUsername = extractionService.getSubject(token);
                final String givenRole = extractionService.getRole(token);

                if (isNoneEmpty(givenUsername)) {
                    final UserDetails userDetails = userDetailsService.loadUserByUsername(givenUsername);

                    if (validationService.isTokenRefreshOne(givenRole)) {
                        setAuthentication(
                                new PreAuthenticatedAuthenticationToken(
                                        userDetails,
                                        null,
                                        Stream.of(givenRole)
                                                .map(SimpleGrantedAuthority::new)
                                                .collect(Collectors.toList())), request);

                    } else if (validationService.isTokenAccessOne(givenRole)) {
                        setAuthentication(new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()), request);

                    }
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
