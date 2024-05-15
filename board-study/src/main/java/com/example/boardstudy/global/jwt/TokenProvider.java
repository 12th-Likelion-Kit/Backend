package com.example.boardstudy.global.jwt;

import com.example.boardstudy.domain.user.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String createToken(String username, UserRole role) {
        Claims claims = Jwts.claims().setSubject("ACCESS_TOKEN");
        claims.put("username", username);
        claims.put("role", role);

        Date now = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 300*1000L))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }
}
