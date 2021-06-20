package com.exadel.discount.util.jwt;

import com.exadel.discount.config.JwtVariablesConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.function.Function;

import static com.exadel.discount.config.JwtVariablesConfig.TOKEN_ENCRYPTION_KEY;

@Service
public abstract class JwtUtil {
    protected final static String ROLE_CLAIM_NAME = "role";
    protected final static String INITIALISATION_TIME_CLAIM_NAME = "initTime";
    protected final static String EXPIRATION_TIME_CLAIM_NAME = "expTime";

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Long extractExpiration(String token) {
        return Long.valueOf(extractAllClaims(token).get(EXPIRATION_TIME_CLAIM_NAME).toString());
    }

    public static String extractRole(String token) {
        return extractAllClaims(token).get(ROLE_CLAIM_NAME).toString();
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(JwtVariablesConfig.getProperty(TOKEN_ENCRYPTION_KEY)).parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token) < Instant.now().getEpochSecond();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> roleClaim = new HashMap<>();
        Map<String, Object> initialTimeClaim = new HashMap<>();
        Map<String, Object> expirationTimeClaim = new HashMap<>();

        roleClaim.put(ROLE_CLAIM_NAME, initRoleClaim(userDetails));
        initialTimeClaim.put(INITIALISATION_TIME_CLAIM_NAME, Instant.now().getEpochSecond());
        expirationTimeClaim.put(EXPIRATION_TIME_CLAIM_NAME, Instant.now().plusSeconds(initExpirationTimeClaim()).getEpochSecond());

        return buildToken(claims, roleClaim, initialTimeClaim, expirationTimeClaim, userDetails.getUsername());
    }

    protected abstract Collection<? extends GrantedAuthority> initRoleClaim(UserDetails userDetails);

    protected abstract long initExpirationTimeClaim();

    protected String buildToken(Map<String, Object> claims,
                                Map<String, Object> roleClaim,
                                Map<String, Object> initialTimeClaim,
                                Map<String, Object> expirationTimeClaim,
                                String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .addClaims(roleClaim)
                .setSubject(subject)
                .addClaims(initialTimeClaim)
                .addClaims(expirationTimeClaim)
                .signWith(SignatureAlgorithm.HS256, JwtVariablesConfig.getProperty(TOKEN_ENCRYPTION_KEY))
                .compact();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}