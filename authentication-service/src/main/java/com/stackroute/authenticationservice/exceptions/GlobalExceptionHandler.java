package com.stackroute.authenticationservice.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{

    @Value(value = "${data.exception.message}")
    private String message;

    @ExceptionHandler(value=UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException)
    {
        return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
    }
}

