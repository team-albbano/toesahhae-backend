package com.example.toesahhae.common.security.exception;

import com.example.toesahhae.common.exception.Error;

public class TokenNotFoundException extends JwtException{
    public TokenNotFoundException(Error error) {
        super(error);
    }
}
