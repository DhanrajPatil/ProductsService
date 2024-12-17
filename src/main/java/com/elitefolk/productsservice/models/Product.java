package com.elitefolk.productsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
