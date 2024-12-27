package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> getProducts(Integer page, Integer size);
    List<Product> getProductByIdOrName(String id) throws ProductNotFoundException;
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    Product partialProduct(String id, Product product);
}
