package com.elitefolk.productsservice.controllerAdvices;

import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(
                "Product with id " + e.getProductId() + " not found",
                org.springframework.http.HttpStatus.NOT_ACCEPTABLE
        );
    }
}
