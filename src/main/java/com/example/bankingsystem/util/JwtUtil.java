package com.example.bankingsystem.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    // Declare the SECRET key with the correct type (Key)
    private static final Key SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Method to generate a token
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // Token expiration (24 hours)
                .signWith(SECRET)  // Use the secret key directly
                .compact();
    }

    // Method to extract the username (subject) from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()  // Use parserBuilder() instead of the deprecated parser()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Method to validate the token by comparing the extracted username with the provided email
    public boolean validateToken(String token, String email) {
        return extractUsername(token).equals(email);
    }
}
