package com.exadel.discount.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public abstract class JwtUtil {
    protected final static String ROLE_CLAIM_NAME = "role";

    private static String TOKEN_ENCRYPTION_KEY;

    @Value("${jwt.encryption.key}")
    public void setTOKEN_ENCRYPTION_KEY(String TOKEN_ENCRYPTION_KEY) {
        JwtUtil.TOKEN_ENCRYPTION_KEY = TOKEN_ENCRYPTION_KEY;
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static String extractRole(String token) {
        return extractAllClaims(token).get(ROLE_CLAIM_NAME, String.class);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(TOKEN_ENCRYPTION_KEY).parseClaimsJws(token).getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).toInstant().isBefore(Instant.now());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> roleClaim = new HashMap<>();

        roleClaim.put(ROLE_CLAIM_NAME, getRole(userDetails));

        return buildToken(
                claims,
                roleClaim,
                userDetails.getUsername());
    }

    protected abstract String getRole(UserDetails userDetails);

    protected abstract long getTokenDuration();

    protected String buildToken(Map<String, Object> claims,
                                Map<String, Object> roleClaim,
                                String subject) {
        Instant currentTime = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .addClaims(roleClaim)
                .setSubject(subject)
                .setIssuedAt(Date.from(currentTime))
                .setExpiration(Date.from(currentTime.plusSeconds(getTokenDuration())))
                .signWith(SignatureAlgorithm.HS256, TOKEN_ENCRYPTION_KEY)
                .compact();
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}