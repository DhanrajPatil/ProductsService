package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Product;

import java.util.List;

public interface ProductsService {
    List<Product> getProducts();
    Product getProductById(String id) throws ProductNotFoundException;
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(String id);
    Product partialProduct(String id, Product product);
}
