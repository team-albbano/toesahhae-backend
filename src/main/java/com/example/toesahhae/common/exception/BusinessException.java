package com.example.toesahhae.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private final Error error;
    public BusinessException(Error error) {
        this.error = error;
    }

    public static BusinessException of(Error error) {
        return new BusinessException(error);
    }
}
