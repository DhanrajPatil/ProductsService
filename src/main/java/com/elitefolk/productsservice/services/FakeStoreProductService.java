package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.dtos.FakeStoreProductDto;
import com.elitefolk.productsservice.dtos.ProductDto;
import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    final RestTemplate restTemplate;
    private final RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable) {
        FakeStoreProductDto[] fakeProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeProduct : fakeProducts) {
            products.add(fakeProduct.toProduct());
        }
        return new PageImpl<>(products);
    }

    @Override
    public List<Product> getProductByIdOrName(String id) throws ProductNotFoundException {

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" + id);
        if(product != null) {
            return List.of(product);
        } else {
            FakeStoreProductDto fakeProduct = restTemplate.getForObject(
                    "https://fakestoreapi.com/products/" + id,
                    FakeStoreProductDto.class
            );
            if(fakeProduct == null) {
                throw new ProductNotFoundException("Product Not Found", id);
            }
            Product prod = fakeProduct.toProduct();
            redisTemplate.opsForHash().put("PRODUCTS", "PRODUCTS_" + id, prod);
            return List.of(prod);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        FakeStoreProductDto fakeProductDto = FakeStoreProductDto.fromProduct(product);
        return restTemplate.postForObject(
                "https://fakestoreapi.com/products/",
                fakeProductDto,
                Product.class
        );
    }

    @Override
    public Product updateProduct(Product product) {
        FakeStoreProductDto fakeProductDto = FakeStoreProductDto.fromProduct(product);
        restTemplate.put(
                "https://fakestoreapi.com/products/" + product.getId(),
                fakeProductDto
        );
        return product;
    }

    @Override
    public void deleteProduct(String id) {
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
    }

    @Override
    public Product partialProduct(String id, Product product) {
        FakeStoreProductDto fakeProductDto = FakeStoreProductDto.fromProduct(product);
        restTemplate.patchForObject(
                "https://fakestoreapi.com/products/" + id,
                fakeProductDto,
                Product.class
        );
        return product;
    }

    @Override
    public Page<ProductDto> getProductsUsingProcedure(Pageable pageable) {
        Page<Product> products = getProducts(pageable);
        return products.map(ProductDto::new);
    }

    @Override
    public Page<ProductDto> fetchProducts(Pageable pageable) {
        Page<Product> products = getProducts(pageable);
        return products.map(ProductDto::new);
    }

    @Override
    public Page<ProductDto> fetchAllProductDtos(Pageable pageable) {
        FakeStoreProductDto[] fakeProducts = restTemplate.getForObject(
                "https://fakestoreapi.com/products/",
                FakeStoreProductDto[].class
        );
        List<ProductDto> products = new ArrayList<>();
        for (FakeStoreProductDto fakeProduct : fakeProducts) {
            products.add(new ProductDto(fakeProduct.toProduct()));
        }
        return new PageImpl<>(products);
    }

    @Override
    public Page<ProductDto>fetchAllProductDtosJpqlJoin(Pageable pageable) {
        return fetchAllProductDtos(pageable);
    }
}
