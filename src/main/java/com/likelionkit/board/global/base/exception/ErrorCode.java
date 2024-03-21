package com.likelionkit.board.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Username 이 중복되었습니다."),
    NOT_FOUND_USER_NAME(HttpStatus.NOT_FOUND, "해당 Username 을 찾을 수 없습니다."),
    MISMATCH_USER_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),

    ;

    private HttpStatus status;
    private String message;
}
