package com.javaclub.springexceptionhandlingdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Message")
public class NoSuchMessageFoundException extends NoSuchElementException {

    public NoSuchMessageFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
