package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.models.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return new Product();
    }

    @GetMapping
    public List<Product> getProducts() {
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return null;
    }

    @PutMapping
    public Product replaceProduct(@RequestBody Product product) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

    }

    @PatchMapping("/{id}")
    public Product partialProduct(@PathVariable Long id, @RequestBody Product product) {
        return null;
    }
}
