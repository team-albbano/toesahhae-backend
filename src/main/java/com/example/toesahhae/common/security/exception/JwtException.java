package com.example.toesahhae.common.security.exception;


import com.example.toesahhae.common.exception.BusinessException;
import com.example.toesahhae.common.exception.Error;

public class JwtException extends BusinessException {
    public JwtException(Error error) {
        super(error);
    }
}
