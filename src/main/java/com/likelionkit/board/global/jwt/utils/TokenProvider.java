package com.likelionkit.board.global.jwt.utils;

import com.likelionkit.board.domain.user.model.UserPrincipal;
import com.likelionkit.board.domain.user.model.UserRole;
import com.likelionkit.board.domain.user.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenProvider implements AuthenticationProvider {
    private static final long MILLI_SECOND = 1000L;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiry-seconds.access-token}")
    private Long accessTokenExpirySeconds;

    private final CustomUserDetailsService customUserDetailsService;

    public String createToken(String userName, UserRole role) {
        Claims claims = Jwts.claims().setSubject("ACCESS_TOKEN");
        claims.put("userName", userName);
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

    public void validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.error("JWT({})가 만료되없습니다. 만료일: {}", token, e.getClaims().getExpiration());
        } catch (RuntimeException e) {
            log.error("JWT({})의 유효성(형식, 서명 등)이 올바르지 않습니다.", token);
        }
    }

    public String getUserNameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("userName");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException { // 실제 인증로직이 담길 메서드
        UserPrincipal userDetails = (UserPrincipal) customUserDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities()); // 인증된 생성자로 Authentication 객체가 생성
    }

    //
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
