package com.elitefolk.productsservice.exceptions;

public class CategoryMissingInProductException extends RuntimeException {
    public CategoryMissingInProductException(String message) {
        super(message);
    }
}
