package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.dtos.FakeStoreProductDto;
import com.elitefolk.productsservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductsService {
    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        RestTemplate temp = new RestTemplate();
        FakeStoreProductDto fakeProduct = temp.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        return fakeProduct.toProduct();
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
