package com.example.boardstudy.global.jwt;

import com.example.boardstudy.domain.user.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider implements AuthenticationProvider {

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

    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return (String) claims.get("username");
        } catch (ExpiredJwtException e) {
            log.error("JWT가 만료되었습니다.");
        } catch (Exception e) {
            log.error("토큰이 유효하지 않습니다.");
        }
        return null;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // UserDetailService를 통해 실제 데이터베이스에서 해당 user가 존재하는지 검사/가져오기
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
