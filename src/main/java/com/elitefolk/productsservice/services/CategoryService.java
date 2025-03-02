package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.dtos.CategoriesUsingProcedureDto;
import com.elitefolk.productsservice.dtos.ProductDto;
import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();
    List<CategoriesUsingProcedureDto> getAllCategoriesUsingProcedure();
    List<Category> getCategoryByIdOrName(String categoryId);
    Category getCategoryByName(String categoryName);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(String categoryId);
    List<Category> getCategoriesByIds(List<UUID> categoryIds);
    List<Category> getCategoriesByNameContains(String containingName);
    List<Product> getProductsForCategory(String categoryName);
    Page<CategoriesUsingProcedureDto> getAllCategoriesJpqlJoin(Pageable pageable);
}
