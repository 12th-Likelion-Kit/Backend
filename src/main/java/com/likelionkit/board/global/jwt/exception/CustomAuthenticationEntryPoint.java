package com.likelionkit.board.global.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelionkit.board.global.base.exception.ErrorCode;
import com.likelionkit.board.global.base.exception.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    // 인증이 실패했을 때 호출되는 메서드
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("인증에 실패했습니다. : {}", authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        String accessDenialResponse = objectMapper.writeValueAsString(new ErrorResponse(ErrorCode.AUTHORIZED_FAIL));
        response.getWriter().write(accessDenialResponse);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
