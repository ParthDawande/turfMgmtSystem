package com.example.turfMgmt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ⚠️ IMPORTANT: HS256 needs at least 32 bytes
    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey12";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // =================== TOKEN GENERATION ===================

    public String generateToken(Long userId, String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .claim("role", role)   // ✅ single role
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // =================== TOKEN EXTRACTION ===================

    public Long extractUserId(String token) {
        return parseClaims(token).get("userId", Long.class);
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return parseClaims(token).get("role", String.class);
    }

    // =================== VALIDATION ===================

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // =================== INTERNAL ===================

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
