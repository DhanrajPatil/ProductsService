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
    private UUID id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
    private UUID categoryId;

    public ProductDto(Product product){
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            this.setCategoryId(product.getCategory().getId());
            this.setCategoryName(product.getCategory().getName());
        }
    }

    public Product toProduct() {
        Product product = new Product();
        if(this.id != null) {
            product.setId(this.id);
        }
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category category = new Category();
        if(this.categoryId != null) {
            category.setId(this.categoryId);
        }
        if(this.categoryName != null) {
            category.setName(this.categoryName);
        }
        if(this.categoryId != null || this.categoryName != null) {
            product.setCategory(category);
        }
        return product;
    }

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(product);
    }

    public static List<Product> fromDtoListToProducts(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(ProductDto::toProduct)
                .collect(Collectors.toList());
    }

    public static List<ProductDto> fromProductsToDtoList(List<Product> products) {
        return products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }
}
