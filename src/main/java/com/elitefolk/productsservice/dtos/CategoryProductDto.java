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
public class CategoryProductDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;

    public CategoryProductDto(Product product){
        this.setId(product.getId().toString());
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setImageUrl(product.getImageUrl());
    }
    private Product getProduct() {
        Product product = new Product();
        if(this.id != null) {
            product.setId(UUID.fromString(this.id));
        }
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        return product;
    }

    public Product toProduct() {
        return getProduct();
    }

    public Product toPersistProduct(Category cat) {
        Product prod = getProduct();
        prod.setCategory(cat);
        return prod;
    }

    public static CategoryProductDto fromProduct(Product product) {
        return new CategoryProductDto(product);
    }

    public static List<Product> fromDtoListToProducts(List<CategoryProductDto> CategoryProductDtos) {
        return CategoryProductDtos.stream()
                .map(CategoryProductDto::toProduct)
                .collect(Collectors.toList());
    }

    public static List<Product> fromDtoListToPersistProducts(List<CategoryProductDto> CategoryProductDtos, Category cat) {
        return CategoryProductDtos.stream()
                .map((catDto) -> catDto.toPersistProduct(cat))
                .collect(Collectors.toList());
    }

    public static List<CategoryProductDto> fromProductsToDtoList(List<Product> products) {
        return products.stream()
                .map(CategoryProductDto::new)
                .collect(Collectors.toList());
    }
}
