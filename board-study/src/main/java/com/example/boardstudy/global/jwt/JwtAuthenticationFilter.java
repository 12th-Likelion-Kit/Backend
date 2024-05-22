package com.example.boardstudy.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 토큰을 꺼낸다.
        String token = resolveToken(request);

        if(token != null ) {
            // 토큰 검증 (만료되었거나, 변조되지 않았는지)
            String username = tokenProvider.validateToken(token);

            if(username != null) {
                try {
                    // 인증되지 않은 Authentication 객체을 생성
                    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, ""));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (RuntimeException e) {
                    log.error("JWT로 부터 인증정보를 만드는데 실패했습니다: {}", e.getMessage());
                }
            }
        }

        filterChain.doFilter(request,response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
