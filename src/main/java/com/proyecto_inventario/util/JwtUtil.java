package com.proyecto_inventario.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtParser;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "secreto123456789012345678901234567890"; // Asegúrate de tener una clave de al menos 256 bits

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        return parser.parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}



