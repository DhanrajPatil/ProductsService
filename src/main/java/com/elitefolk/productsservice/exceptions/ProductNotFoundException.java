package com.elitefolk.productsservice.exceptions;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductNotFoundException extends RuntimeException{
    private final String productId;

    public ProductNotFoundException(String message, String id) {
        super(message);
        this.productId = id;
    }
}
