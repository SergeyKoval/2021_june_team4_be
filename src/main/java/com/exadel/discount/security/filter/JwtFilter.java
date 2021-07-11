package com.exadel.discount.security.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.exadel.discount.model.exception.ExceptionCause;
import com.exadel.discount.model.exception.ExceptionDetails;
import com.exadel.discount.service.impl.JwtServiceImpl;
import com.exadel.discount.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;

import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringAfter;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;

@Component
@Setter
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final String AUTHORIZATION_HEADER_TYPE = "Bearer ";
    private final String ENCODING_UTF8 = "UTF-8";

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtServiceImpl jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (SecurityContextHolder.getContext().getAuthentication() == null
                    && startsWith(authorizationHeader, AUTHORIZATION_HEADER_TYPE)) {
                final String token = substringAfter(authorizationHeader, AUTHORIZATION_HEADER_TYPE);
                final String givenUsername = jwtService.getSubject(token);
                final String givenRole = jwtService.getRole(token);

                if (isNoneEmpty(givenUsername)) {
                    final UserDetails userDetails = userDetailsService.loadUserByUsername(givenUsername);

                    Collection<? extends GrantedAuthority> permissions = StringUtils.equals(givenRole, jwtService.REFRESH_ROLE) ?
                            Collections.singletonList(new SimpleGrantedAuthority(givenRole)) : userDetails.getAuthorities();

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, permissions);

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JWTDecodeException exception) {
            setErrorResponse(response, HttpStatus.FORBIDDEN, exception, ExceptionCause.INCORRECT_TOKEN);
        } catch (TokenExpiredException exception) {
            setErrorResponse(response, HttpStatus.FORBIDDEN, exception, ExceptionCause.EXPIRED_TOKEN);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    public void setErrorResponse(HttpServletResponse response, HttpStatus httpStatus, Exception exception,
                                 ExceptionCause cause) {
        try {
            response.setStatus(httpStatus.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(ENCODING_UTF8);

            JSONObject jsonException = new JSONObject(
                    ExceptionDetails.builder()
                            .message(exception.getMessage())
                            .cause(cause)
                            .build());

            PrintWriter out = response.getWriter();
            out.print(jsonException);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

