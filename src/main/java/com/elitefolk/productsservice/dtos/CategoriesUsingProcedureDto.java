package com.elitefolk.productsservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesUsingProcedureDto {
    private UUID categoryId;
    private String categoryName;
    private UUID productId;
    private String productName;
    private String productDescription;
    private Double price;
    private String imageUrl;
}
