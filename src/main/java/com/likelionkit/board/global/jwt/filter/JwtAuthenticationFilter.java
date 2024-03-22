package com.likelionkit.board.global.jwt.filter;

import com.likelionkit.board.global.jwt.utils.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        if(token != null) { // 토큰이 존재한다면
            // 토큰의 유효성 체크
            tokenProvider.validateToken(token);
            String userName = tokenProvider.getUserNameFromToken(token);

            try {
                Authentication authentication = tokenProvider.authenticate(new UsernamePasswordAuthenticationToken(userName,""));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (RuntimeException e) { // server error
                log.error("JWT({})로부터 인증정보를 만드는데 실패했습니다: {}", token, e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){ // BearerToken 이 맞는지 검사
            return bearerToken.substring(BEARER_PREFIX.length()); // 토큰 추출
        }
        return null;
    }
}
