package com.ml.ProductsApi.exception;

import com.ml.ProductsApi.exception.concreteExceptions.ApiException;
import com.ml.ProductsApi.exception.concreteExceptions.NoStockException;
import com.ml.ProductsApi.exception.concreteExceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {NoStockException.class})
    public ResponseEntity<Object> handleApiRequestException(NoStockException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundRequestException(ProductNotFoundException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
}
