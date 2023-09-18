package com.example.toesahhae.common.security.exception;


import com.example.toesahhae.common.exception.Error;

public class ExpiredTokenException extends JwtException{
    public ExpiredTokenException(Error error) {
        super(error);
    }
}
