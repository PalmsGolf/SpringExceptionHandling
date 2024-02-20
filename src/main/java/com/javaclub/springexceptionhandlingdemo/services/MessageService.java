package com.javaclub.springexceptionhandlingdemo.services;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.javaclub.springexceptionhandlingdemo.exceptions.NoSuchMessageFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

    public String getMessageWithNoSuchMessageFoundExceptionThrow() throws NoSuchMessageFoundException {
        throw new NoSuchMessageFoundException("Message not found");
    }

    public String getMessageWithHttpMessageNotReadableExceptionThrow() throws HttpMessageNotReadableException {
        throw new HttpMessageNotReadableException("Message not readable");
    }

    public String getMessageWithCustomMessageNotReadableExceptionThrow() {
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        final String enumValue = "some_wrong_enum_value";
        final String enumFiledName = "some_enum_field_name";
        final InvalidFormatException enumException = new InvalidFormatException(null, "some_message", enumValue, Status.class);
        enumException.prependPath(null, enumFiledName);

        throw new HttpMessageNotReadableException("some_dummy_message", enumException, null);
    }

    public enum Status {
        ON,
        OF
    }
}
