package com.example.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔹 Load values from application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration; // in milliseconds (900000 = 15 mins)

    // 🔹 Generate token for a given username
    public String generateToken(String username) {
        // convert secret key to cryptographic key
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        // build the token
        return Jwts.builder()
                .setSubject(username)  // user info (who logged in)
                .setIssuedAt(new Date())  // current time
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // expires in 15 min
                .signWith(key, SignatureAlgorithm.HS256) // algorithm + secret key
                .compact(); // final string token
    }

    // 🔹 Extract username (subject) from token
    public String extractUsername(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 🔹 Check if token is valid (not expired and correctly signed)
    public boolean validateToken(String token, String username) {
        try {
            Key key = Keys.hmacShaKeyFor(secret.getBytes());
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true; // ✅ valid
        } catch (ExpiredJwtException e) {
            System.out.println("❌ Token expired");
        } catch (JwtException e) {
            System.out.println("❌ Invalid token: " + e.getMessage());
        }
        return false; // ❌ invalid
    }
}
