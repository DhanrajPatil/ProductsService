package com.elitefolk.productsservice.exceptions;

import com.elitefolk.productsservice.dtos.CategoryDto;
import com.elitefolk.productsservice.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class CategoryAlreadyPresentException extends RuntimeException{
    private CategoryDto categoryDto;

    public CategoryAlreadyPresentException(Category category, String message) {
        super(message);
        category.setProducts(new ArrayList<>());
        this.categoryDto = new CategoryDto(category);
    }
}
