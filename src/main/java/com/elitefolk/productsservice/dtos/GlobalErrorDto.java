package com.elitefolk.productsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalErrorDto<T> {
    private String message;
    private String error;
    private String status;
    private T errRelatedData;

    public GlobalErrorDto(String message, String error, String status) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.errRelatedData = null;
    }
}
