package com.dorra.Project.Management.System.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtProvider {
    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {
        List<String> authorities = auth.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .claim("authorities", authorities)
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {
        // Check if jwt is null
        if (jwt == null) {
            return null; // or throw an exception or handle it according to your application logic
        }

        // Check if the token starts with "Bearer " and remove it if present
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7).trim();
        } else {
            jwt = jwt.trim();
        }

        // Parse the JWT token
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        // Extract the email from the claims
        return claims.get("email", String.class);
    }


}
