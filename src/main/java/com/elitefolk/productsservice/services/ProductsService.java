package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Product;

import java.util.List;

public interface ProductsService {
    List<Product> getProducts();
    Product getProductById(Long id) throws ProductNotFoundException;
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    Product partialProduct(Long id, Product product);
}
