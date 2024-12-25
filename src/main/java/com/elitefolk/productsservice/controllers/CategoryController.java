package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.dtos.ProductDto;
import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{categoryIdOrName}")
    public List<Category> getCategoryById(@PathVariable String categoryIdOrName) {
        return this.categoryService.getCategoryByIdOrName(categoryIdOrName);
    }

    @GetMapping("/{categoryName}/products")
    public List<ProductDto> getProductsForCategory(@PathVariable String categoryName) {
        List<Product> products = this.categoryService.getProductsForCategory(categoryName);
        return ProductDto.fromProductsToDtoList(products);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category cat = this.categoryService.addCategory(category);
        URI location = URI.create("/categories/" + cat.getId());
        return ResponseEntity.created(location).body(cat);
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
