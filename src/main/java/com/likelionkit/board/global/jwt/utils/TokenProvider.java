package com.likelionkit.board.global.jwt.utils;

import com.likelionkit.board.domain.user.model.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider {
    private static final long MILLI_SECOND = 1000L;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiry-seconds.access-token}")
    private Long accessTokenExpirySeconds;

    public String createToken(Long userId, UserRole role) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId)); // JWT payload 에 저장되는 정보단위
        claims.put("roles", role); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + accessTokenExpirySeconds*MILLI_SECOND)) // set Expire Time
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)  // 사용할 암호화 알고리즘(HMAC-SHA)
                // signature 에 들어갈 secret값 세팅
                .compact();
    }
}
