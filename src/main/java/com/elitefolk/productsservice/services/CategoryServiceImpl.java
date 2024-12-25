package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.exceptions.CategoryNotFoundException;
import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.repositories.CategoryRepository;
import com.elitefolk.productsservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductRepository productRepo) {
        this.categoryRepository = categoryRepository;
        this.productRepo = productRepo;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAllByIsDeleted(false);
    }

    @Override
    public List<Category> getCategoryByIdOrName(String categoryIdOrName) {
        try{
            UUID id = UUID.fromString(categoryIdOrName);
            Category cat = this.categoryRepository.findById(id)
                    .orElseThrow(() -> new CategoryNotFoundException("Category with id " + categoryIdOrName + " not found"));
            return List.of(cat);
        } catch (IllegalArgumentException e) {
            return this.categoryRepository.findByNameContains(categoryIdOrName);
        }
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

    @Override
    public List<Product> getProductsForCategory(String categoryName) {
        Category cat = this.categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category with name " + categoryName + " not found"));
        return this.productRepo.findByCategoryNameIgnoreCase(categoryName);
    }
}
