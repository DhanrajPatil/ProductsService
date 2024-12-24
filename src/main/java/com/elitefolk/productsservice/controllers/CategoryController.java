package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable String categoryId) {
        return this.categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/name/{categoryName}")
    public Category getCategoryByName(@PathVariable String categoryName) {
        return this.categoryService.getCategoryByName(categoryName);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return this.categoryService.addCategory(category);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }
}