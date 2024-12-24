package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.exceptions.CategoryNotFoundException;
import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAllByIsDeleted(false);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return this.categoryRepository.findById(UUID.fromString(categoryId))
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + categoryId + " not found"));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return this.categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + categoryName + " not found"));
    }

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String categoryId) {
        this.categoryRepository.deleteById(UUID.fromString(categoryId));
    }

    @Override
    public List<Category> getCategoriesByIds(List<UUID> categoryIds) {
        return this.categoryRepository.findByIdIn(categoryIds);
    }

    @Override
    public List<Category> getCategoriesByNameContains(String containingName) {
        return this.categoryRepository.findByNameContains(containingName);
    }
}
