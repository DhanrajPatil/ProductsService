package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        return productsService.getProductById(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productsService.getProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productsService.saveProduct(product);
    }

    @PutMapping
    public Product replaceProduct(@RequestBody Product product) {
        return productsService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public Product partialProduct(@PathVariable Long id, @RequestBody Product product) {
        return productsService.partialProduct(id, product);
    }
}
