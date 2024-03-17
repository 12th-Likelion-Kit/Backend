package com.likelionkit.board.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private ErrorCode errorCode;

}
