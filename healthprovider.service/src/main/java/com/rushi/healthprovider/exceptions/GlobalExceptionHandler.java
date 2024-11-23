package com.rushi.healthprovider.exceptions;


import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleValidationException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error-> error.getField()+": "+error.getDefaultMessage())
                .toList();

        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, errors, LocalDateTime.now(), ex.getBindingResult().getObjectName());

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIError> handleConstraintViolationException(ConstraintViolationException ex){

        List<String> errors =  ex.getConstraintViolations()
                .stream()
                .map(violation-> violation.getPropertyPath() + ": "+ violation.getMessage())
                .toList();

        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, errors, LocalDateTime.now(), "/create");

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
