package com.elitefolk.productsservice.controllerAdvices;

import com.elitefolk.productsservice.dtos.CategoryDto;
import com.elitefolk.productsservice.dtos.GlobalErrorDto;
import com.elitefolk.productsservice.exceptions.CategoryAlreadyPresentException;
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
    public ResponseEntity<GlobalErrorDto<Void>> handleProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto<Void>(
                    e.getMessage(),
                    "Product not found",
                    HttpStatus.NOT_FOUND.getReasonPhrase()
            ),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<GlobalErrorDto<Void>> handleCategoryNotFoundException(CategoryNotFoundException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto<Void>(e.getMessage(), "Category not found", HttpStatus.NOT_FOUND.getReasonPhrase()),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(CategoryMissingInProductException.class)
    public ResponseEntity<GlobalErrorDto<Void>> handleCategoryMissingInProductException(CategoryMissingInProductException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto<Void>(
                e.getMessage(),
                "Category missing in product",
                HttpStatus.BAD_REQUEST.getReasonPhrase()
            ),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(CategoryAlreadyPresentException.class)
    public ResponseEntity<GlobalErrorDto<CategoryDto>> handleCategoryAlreadyPresentException(CategoryAlreadyPresentException e) {
        return new ResponseEntity<>(
            new GlobalErrorDto<CategoryDto>(
                e.getMessage(),
                "Category already present in the database",
                HttpStatus.ALREADY_REPORTED.getReasonPhrase(),
                e.getCategoryDto()
            ),
            HttpStatus.OK
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalErrorDto<Void>> handleException(Exception e) {
        return new ResponseEntity<>(
            new GlobalErrorDto<Void>(
                e.getMessage(),
            "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
            ),
            HttpStatus.OK
        );
    }
}
