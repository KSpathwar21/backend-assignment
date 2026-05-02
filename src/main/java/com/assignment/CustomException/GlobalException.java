package com.assignment.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponseFormat> handleRsourceException(ResourceNotFoundException ex){
        ErrorResponseFormat errorResponseFormat = new ErrorResponseFormat();
        errorResponseFormat.setError(Errors.RESOURCE_NOT_FOUND);
        errorResponseFormat.setMessage(ex.getMessage());
        errorResponseFormat.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseFormat);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponseFormat> handleGeneral(Exception ex){
        ErrorResponseFormat errorResponseFormat = new ErrorResponseFormat();
        errorResponseFormat.setError(Errors.GENERAL_ERROR);
        errorResponseFormat.setMessage(ex.getMessage());
        errorResponseFormat.setTimeStamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseFormat);
    }
}
