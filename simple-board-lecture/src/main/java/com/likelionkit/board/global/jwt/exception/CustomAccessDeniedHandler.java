package com.likelionkit.board.global.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelionkit.board.global.base.exception.ErrorCode;
import com.likelionkit.board.global.base.exception.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        String accessDenialResponse = objectMapper.writeValueAsString(new ErrorResponse(ErrorCode.ACCESS_DENIED));
        response.getWriter().write(accessDenialResponse);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
