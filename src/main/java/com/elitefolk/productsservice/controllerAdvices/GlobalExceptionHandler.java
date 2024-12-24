package com.elitefolk.productsservice.controllerAdvices;

import com.elitefolk.productsservice.dtos.GlobalErrorDto;
import com.elitefolk.productsservice.exceptions.CategoryMissingInProductException;
import com.elitefolk.productsservice.exceptions.CategoryNotFoundException;
import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<GlobalErrorDto> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto(e.getMessage(), "Product not found", HttpStatus.NOT_FOUND.getReasonPhrase()),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<GlobalErrorDto> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto(e.getMessage(), "Category not found", HttpStatus.NOT_FOUND.getReasonPhrase()),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(CategoryMissingInProductException.class)
    public ResponseEntity<GlobalErrorDto> handleCategoryMissingInProductException(CategoryMissingInProductException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto(
                e.getMessage(),
                "Category missing in product",
                HttpStatus.BAD_REQUEST.getReasonPhrase()
            ),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorDto> handleException(Exception e) {
        return new ResponseEntity<>(
            new GlobalErrorDto(
                e.getMessage(),
            "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
            ),
            HttpStatus.OK
        );
    }
}
