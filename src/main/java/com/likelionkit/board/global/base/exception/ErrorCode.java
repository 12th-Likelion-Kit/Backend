package com.likelionkit.board.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Username 이 중복되었습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 User 를 찾을 수 없습니다."),
    MISMATCH_USER_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    NOT_AUTHORIZED_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    AUTHORIZED_FAIL(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),

    ;

    private HttpStatus status;
    private String message;
}
