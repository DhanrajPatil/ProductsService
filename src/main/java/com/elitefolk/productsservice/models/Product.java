package com.elitefolk.productsservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
