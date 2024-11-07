package com.scaler.ecommerceproject.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class, ArrayIndexOutOfBoundsException.class,
                       NoSuchElementException.class})
    public ResponseEntity<String> handleException(Exception ex){
        return new ResponseEntity<>( ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
