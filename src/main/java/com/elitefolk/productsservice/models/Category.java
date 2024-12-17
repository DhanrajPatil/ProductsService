package com.elitefolk.productsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Category extends BaseModel{
    private String name;

    public Category(Long id, String createdBy, String updatedBy, Long createdDate, Long updatedDate, Boolean isDeleted, String name) {
        super(id, createdBy, updatedBy, createdDate, updatedDate, isDeleted);
        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
