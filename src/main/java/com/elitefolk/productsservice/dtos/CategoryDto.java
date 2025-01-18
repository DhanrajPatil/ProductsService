package com.elitefolk.productsservice.dtos;

import com.elitefolk.productsservice.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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

    public static List<CategoryDto> fromProcedureCategories(List<CategoriesUsingProcedureDto> categories) {
        Map<String, List<CategoryProductDto>> catMap = new HashMap<>();
        for(CategoriesUsingProcedureDto cat : categories) {
            String key = cat.getCategoryId().toString();
            if(catMap.containsKey(key)) {
                catMap.get(key).add(
                        new CategoryProductDto(cat.getProductId().toString(), cat.getProductName(), cat.getProductDescription(), cat.getPrice(), cat.getImageUrl())
                );
            } else {
                List<CategoryProductDto> prods = new ArrayList<>();
                if(cat.getProductId() != null) {
                    prods.add(new CategoryProductDto(cat.getProductId().toString(), cat.getProductName(), cat.getProductDescription(), cat.getPrice(), cat.getImageUrl()));
                }
                catMap.put(key, prods);
            }
        }
        List<CategoryDto> cats = new ArrayList<>();
        Set<String> catIds = new HashSet<>();
        for(CategoriesUsingProcedureDto cat : categories) {
            String key = cat.getCategoryId().toString();
            if(catIds.contains(key)) {
                continue;
            }
            catIds.add(key);
            if(catMap.containsKey(key)) {
                cats.add(new CategoryDto(cat.getCategoryId().toString(), cat.getCategoryName(), catMap.get(key)));
            }
        }
        return cats;
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
