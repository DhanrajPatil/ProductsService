package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.dtos.FakeStoreProductDto;
import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductsService {
    final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getProducts() {
        FakeStoreProductDto[] fakeProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeProduct : fakeProducts) {
            products.add(fakeProduct.toProduct());
        }
        return products;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeProduct = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        if(fakeProduct == null) {
            throw new ProductNotFoundException("Product Not Found", id);
        }
        return fakeProduct.toProduct();
    }

    @Override
    public Product saveProduct(Product product) {
        FakeStoreProductDto fakeProductDto = new FakeStoreProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName(),
                product.getDescription(),
                product.getImageUrl()
        );
        return restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                fakeProductDto,
                Product.class
        );
    }

    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto fakeProductDto = new FakeStoreProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory().getName(),
                product.getDescription(),
                product.getImageUrl()
        );
        restTemplate.put(
                "https://fakestoreapi.com/products/" + product.getId(),
                fakeProductDto
        );
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public Product partialProduct(Long id, Product product) {
        FakeStoreProductDto fakeProductDto = new FakeStoreProductDto();
        fakeProductDto.setId(product.getId());
        fakeProductDto.setPrice(product.getPrice());
        fakeProductDto.setDescription(product.getDescription());
        fakeProductDto.setImage(product.getImageUrl());
        restTemplate.patchForObject(
                "https://fakestoreapi.com/products/" + id,
                fakeProductDto,
                Product.class
        );
        return product;
    }
}
