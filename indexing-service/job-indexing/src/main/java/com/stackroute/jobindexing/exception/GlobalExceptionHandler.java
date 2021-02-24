package com.stackroute.jobindexing.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value(value="${data.exception.messageAlreadyExists}")
    private String messageAlreadyExists;

    @ExceptionHandler(value=JobAlreadyExistsException.class)
    public ResponseEntity<String> jobAlreadyExists(JobAlreadyExistsException jobAlreadyExistsException)
    {
        return new ResponseEntity<>(messageAlreadyExists, HttpStatus.CONFLICT);
    }

}
