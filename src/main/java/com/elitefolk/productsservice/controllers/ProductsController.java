package com.elitefolk.productsservice.controllers;

import com.elitefolk.productsservice.dtos.PaginationResponse;
import com.elitefolk.productsservice.dtos.ProductDto;
import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.services.ProductService;
import com.elitefolk.productsservice.utilities.PagingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    ProductService productsService;

    // Constructor Injection is recommended as it is a good practice
    // it is recommended by Spring
    // it will be easy task to inject it with constructor injection
    // as we can pass the mock object in the constructor
    // it will be injected into the controller
    // we can test the controller
    /*    public ProductsController(@Qualifier("productServiceImpl") ProductService productsService) {
        this.productsService = productsService;
    } */

    @GetMapping("/{idOrName}")
    public List<ProductDto> getProduct(@PathVariable("idOrName") String idOrName) {
        List<Product> products = productsService.getProductByIdOrName(idOrName);
        return ProductDto.fromProductsToDtoList(products);
    }

    @GetMapping
    public PaginationResponse<ProductDto> getProducts(Pageable pageable) {
        Page<Product> products = productsService.getProducts(pageable);
        Page<ProductDto> productsPage = products.map(ProductDto::new);
        return new PaginationResponse<>(productsPage);
    }

    @GetMapping("/using/namesNativeQuery")
    public PaginationResponse<ProductDto> getProductsByNamedNativeQuery(Pageable pageable) {
        Page<ProductDto> productsPage = productsService.fetchProducts(pageable);
        return new PaginationResponse<>(productsPage);
    }

    @GetMapping("/using/jpaQuery")
    // using directly pageable, sorting direction not working
    // sorting direction is getting attached by default ASC to using custom.
    //public PaginationResponse<ProductDto> getProductsByJpaQuery(Pageable pageable) {
    public PaginationResponse<ProductDto> getProductsByJpaQuery(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PagingUtility.getPageable(page, size, sort, direction);
        Page<ProductDto> dtos = productsService.fetchAllProductDtos(pageable);
        return new PaginationResponse<>(dtos);
    }

    @GetMapping("/using/jpqlJoin")
    public PaginationResponse<ProductDto> getProductsByJpqlJoin(Integer page, Integer size, String sort, String direction) {
        Pageable pageable = PagingUtility.getPageable(page, size, sort, direction);
        Page<ProductDto> dtos = productsService.fetchAllProductDtosJpqlJoin(pageable);
        return new PaginationResponse<>(dtos);
    }

    @GetMapping("/using/procedure")
    public PaginationResponse<ProductDto> getProductsUsingProcedure(Pageable pageable) {
        Page<ProductDto> products = productsService.getProductsUsingProcedure(pageable);
        return new PaginationResponse<>(products);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product prod = productsService.saveProduct(productDto.toProduct());
        ProductDto dto = new ProductDto(prod);
        URI location = URI.create("/products/" + dto.getId());
        return ResponseEntity.created(location).body(dto);
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
