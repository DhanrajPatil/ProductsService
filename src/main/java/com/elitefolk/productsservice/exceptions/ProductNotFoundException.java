package com.elitefolk.productsservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{
    private final Long productId;

    public ProductNotFoundException(String message, long id) {
        super(message);
        this.productId = id;
    }
}
