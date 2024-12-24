package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    // Field Injection is not recommended as it is not a good practice
    // it is not recommended by Spring
    // For testing we would have to create a mock object of the service
    // and inject it into the controller
    // so it will be difficult task to inject it with field injection
    @Autowired
    @Qualifier("productServiceImpl")
    ProductsService productsService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") String id) {
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
    public void deleteProduct(@PathVariable String id) {
        productsService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public Product partialProduct(@PathVariable String id, @RequestBody Product product) {
        return productsService.partialProduct(id, product);
    }
}
