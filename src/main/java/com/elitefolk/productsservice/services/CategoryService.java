package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.models.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(String categoryId);
    Category getCategoryByName(String categoryName);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(String categoryId);
    List<Category> getCategoriesByIds(List<UUID> categoryIds);
    List<Category> getCategoriesByNameContains(String containingName);
}