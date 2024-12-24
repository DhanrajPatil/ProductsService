package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.dtos.ProductDto;
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
    // it will be difficult task to inject it with field injection
    @Autowired
    @Qualifier("productServiceImpl")
    ProductsService productsService;

    // Constructor Injection is recommended as it is a good practice
    // it is recommended by Spring
    // it will be easy task to inject it with constructor injection
    // as we can pass the mock object in the constructor
    // it will be injected into the controller
    // we can test the controller
    /*    public ProductsController(@Qualifier("productServiceImpl") ProductsService productsService) {
        this.productsService = productsService;
    } */

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") String id) {
        Product prod = productsService.getProductById(id);
        return new ProductDto(prod);
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        List<Product> products = productsService.getProducts();
        return ProductDto.fromProductsToDtoList(products);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product prod = productsService.saveProduct(productDto.toProduct());
        return new ProductDto(prod);
    }

    @PutMapping
    public ProductDto replaceProduct(@RequestBody ProductDto productDto) {
        Product prod = productsService.updateProduct(productDto.toProduct());
        return new ProductDto(prod);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productsService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductDto partialProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        Product prod = productsService.partialProduct(id, productDto.toProduct());
        return new ProductDto(prod);
    }
}
