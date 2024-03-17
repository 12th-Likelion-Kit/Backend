package com.likelionkit.board.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "Username 이 중복되었습니다."),

    ;

    private HttpStatus status;
    private String message;
}
