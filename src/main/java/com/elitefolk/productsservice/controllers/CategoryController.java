package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.dtos.CategoryDto;
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
    public List<CategoryDto> getCategories() {
        return CategoryDto.fromCategoriesToDtoList(this.categoryService.getAllCategories());
    }

    @GetMapping("/{categoryIdOrName}")
    public List<CategoryDto> getCategoryByIdOrName(@PathVariable String categoryIdOrName) {
        List<Category> categories = this.categoryService.getCategoryByIdOrName(categoryIdOrName);
        return CategoryDto.fromCategoriesToDtoList(categories);
    }

    @GetMapping("/{categoryName}/products")
    public List<ProductDto> getProductsForCategory(@PathVariable String categoryName) {
        List<Product> products = this.categoryService.getProductsForCategory(categoryName);
        return ProductDto.fromProductsToDtoList(products);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        Category cat = this.categoryService.addCategory(categoryDto.toCascadePersistCategory());
        URI location = URI.create("/categories/" + cat.getId());
        return ResponseEntity.created(location).body(new CategoryDto(cat));
    }

    @PutMapping
    public CategoryDto updateCategory(@RequestBody Category category) {
        return new CategoryDto(this.categoryService.updateCategory(category));
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }
}
