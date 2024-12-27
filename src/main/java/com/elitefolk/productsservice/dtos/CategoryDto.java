package com.elitefolk.productsservice.dtos;

import com.elitefolk.productsservice.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private String name;
    private List<CategoryProductDto> products;

    public CategoryDto(Category category) {
        this.id = category.getId().toString();
        this.name = category.getName();
        if(category.getProducts() != null) {
            this.products = CategoryProductDto.fromProductsToDtoList(
                    category.getProducts()
            );
        }
    }

    public static List<CategoryDto> fromCategoriesToDtoList(List<Category> categories) {
        return categories.stream()
                .map(CategoryDto::new)
                .toList();
    }

    public static CategoryDto fromCategoryToDto(Category category) {
        return new CategoryDto(category);
    }

    public Category toCategory() {
        Category cat = getCategory();
        if(this.getProducts() != null) {
            cat.setProducts(CategoryProductDto.fromDtoListToProducts(this.getProducts()));
        }
        return cat;
    }

    public Category toCascadePersistCategory() {
        Category cat = getCategory();
        if(this.getProducts() != null) {
            cat.setProducts(CategoryProductDto.fromDtoListToPersistProducts(this.getProducts(), cat));
        }
        return cat;
    }

    private Category getCategory() {
        Category category = new Category();
        if(this.getId() != null) {
            try {
                category.setId(UUID.fromString(this.getId()));
            } catch (IllegalArgumentException e) {
                category.setId(null);
            }
        }
        category.setName(this.getName());
        return category;
    }

}
