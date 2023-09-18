package com.example.toesahhae.common.ResponseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {

    // Common
    OK(HttpStatus.OK, "정상적으로 처리되었습니다.", "O200"),

    ;

    private final HttpStatus httpStatus;
    private final String message;
    private final String code;

    ResponseCode(HttpStatus httpStatus, String message, String code) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }
}
