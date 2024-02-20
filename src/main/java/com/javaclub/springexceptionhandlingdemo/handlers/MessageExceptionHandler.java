package com.javaclub.springexceptionhandlingdemo.handlers;

import com.javaclub.springexceptionhandlingdemo.exceptions.CustomAuthenticationException;
import com.javaclub.springexceptionhandlingdemo.exceptions.NoSuchMessageFoundException;
import com.javaclub.springexceptionhandlingdemo.utils.ShouldBeHandledByControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
//@ControllerAdvice("com.javaclub.springexceptionhandlingdemo")
//@ControllerAdvice(annotations = ShouldBeHandledByControllerAdvice.class)
public class MessageExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchMessageFoundException.class)
    public ResponseEntity<String> handleNoSuchMessageFoundException(@NonNull final NoSuchMessageFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."From MessageExceptionHandler. Error: \{ex.getMessage()} ");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("From handle all exceptions");
    }

    @ResponseBody
    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<String> handleCustomAuthenticationException() {

        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).body("no access");
    }
}

