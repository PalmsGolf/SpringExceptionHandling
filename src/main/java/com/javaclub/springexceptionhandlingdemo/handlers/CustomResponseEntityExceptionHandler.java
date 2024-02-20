package com.javaclub.springexceptionhandlingdemo.handlers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;
import java.util.Arrays;

//@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        {
            String errorMessage = "Required request body is missing";
            final Throwable exceptionCause = ex.getCause();

            if (exceptionCause instanceof final InvalidFormatException invalidFormatException) {
                final String value = invalidFormatException.getValue().toString();
                final String fieldName = invalidFormatException.getPath().get(invalidFormatException.getPath().size() - 1).getFieldName();

                if (invalidFormatException.getTargetType().isEnum()) {
                    final String possibleEnumValues = Arrays.toString(invalidFormatException.getTargetType().getEnumConstants());
                    errorMessage = String.format("Invalid Enum value: '%s' for the field: '%s'. Possible values are: %s", value, fieldName, possibleEnumValues);
                } else if (invalidFormatException.getTargetType().isAssignableFrom(ZonedDateTime.class)) {
                    errorMessage = String.format("Invalid ZonedDateTime value: '%s' for the field: '%s'", value, fieldName);
                } else if (invalidFormatException.getTargetType().isAssignableFrom(Boolean.class)) {
                    errorMessage = String.format("Invalid Boolean value: '%s' for the field: '%s'", value, fieldName);
                } else {
                    errorMessage = String.format("Invalid value: '%s' for the field: '%s'", value, fieldName);
                }
            } else if (exceptionCause instanceof final JsonMappingException jsonMappingException) {
                errorMessage = String.format("Required request body is missing. Exception: '%s'", jsonMappingException.getOriginalMessage());
            }

            return ResponseEntity.status(status).headers(headers).body(errorMessage);
        }
    }
}