package com.elitefolk.productsservice.dtos;

import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
    private String categoryId;

    public ProductDto(Product product){
        this.setId(product.getId().toString());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setImageUrl(product.getImageUrl());
        this.setCategoryId(product.getCategory().getId().toString());
        this.setCategoryName(product.getCategory().getName());
    }

    public Product toProduct() {
        Product product = new Product();
        if(this.id != null) {
            product.setId(UUID.fromString(this.id));
        }
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category category = new Category();
        if(this.categoryId != null) {
            category.setId(UUID.fromString(this.categoryId));
        }
        category.setName(this.categoryName);
        product.setCategory(category);
        return product;
    }

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(product);
    }


    public static List<ProductDto> fromProductsToDtoList(List<Product> products) {
        return products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }
}
