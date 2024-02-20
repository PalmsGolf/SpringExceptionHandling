package com.javaclub.springexceptionhandlingdemo.exceptions;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(final String msg) {
        super(msg);
    }
}
