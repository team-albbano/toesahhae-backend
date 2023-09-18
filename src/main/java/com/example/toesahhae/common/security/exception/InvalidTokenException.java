package com.example.toesahhae.common.security.exception;


import com.example.toesahhae.common.exception.Error;

public class InvalidTokenException extends JwtException {
    public InvalidTokenException(Error error) {
        super(error);
    }
}
