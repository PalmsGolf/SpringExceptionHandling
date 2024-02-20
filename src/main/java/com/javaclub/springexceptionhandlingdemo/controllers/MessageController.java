package com.javaclub.springexceptionhandlingdemo.controllers;

import com.javaclub.springexceptionhandlingdemo.exceptions.NoSuchMessageFoundException;
import com.javaclub.springexceptionhandlingdemo.services.MessageService;
import com.javaclub.springexceptionhandlingdemo.utils.ShouldBeHandledByControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/messages")
@ShouldBeHandledByControllerAdvice
public class MessageController {
    private final MessageService messageService;

    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/exception_handling/controller")
    public ResponseEntity<String> getMessageWhenControllerAdviceExceptionHandling() {
        final String responseBody = this.messageService.getMessageWithNoSuchMessageFoundExceptionThrow();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/exception_handling/response_entity")
    public ResponseEntity<String> getMessageWhenResponseEntityExceptionHandling() {
        //final String responseBody = this.messageService.getMessageWithHttpMessageNotReadableExceptionThrow();
        final String responseBody = this.messageService.getMessageWithCustomMessageNotReadableExceptionThrow();

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/exception_handling/response_status")
    public ResponseEntity<String> getMessageWhenResponseStatusExceptionHandling() {
        String responseBody;
        try {
            responseBody = this.messageService.getMessageWithNoSuchMessageFoundExceptionThrow();
        } catch (NoSuchMessageFoundException ex) {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }

        return ResponseEntity.ok(responseBody);
    }

    //@ExceptionHandler(NoSuchMessageFoundException.class)
    public ResponseEntity<String> NoSuchMessageFoundExceptionHandler(NoSuchMessageFoundException ex) {
        final String exceptionMessage = ex.getMessage();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(STR."Error: \{exceptionMessage} ");
    }
}
