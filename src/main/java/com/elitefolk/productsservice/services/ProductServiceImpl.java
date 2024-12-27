package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.exceptions.CategoryMissingInProductException;
import com.elitefolk.productsservice.exceptions.CategoryNotFoundException;
import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.repositories.CategoryRepository;
import com.elitefolk.productsservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> getProducts(Integer page, Integer size) {
        return this.productRepository.findByIsDeletedFalse(
                PageRequest.of(page, size)
        );
    }

    @Override
    public List<Product> getProductByIdOrName(String idOrName) {
        try {
            UUID uuid = UUID.fromString(idOrName);
            Product product = this.productRepository.findById(uuid)
                    .orElseThrow(() -> new ProductNotFoundException("Product with id " + idOrName + " not found", idOrName));
            return List.of(product);
        } catch (IllegalArgumentException e) {
            return this.productRepository.findByNameContains(idOrName);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        Category cr = product.getCategory();
        if(cr != null) {
            if(cr.getId() == null && cr.getName() == null) {
                throw new CategoryMissingInProductException("Category is required, It cannot be empty");
            } else if(cr.getId() == null) {
                Category category = this.categoryRepository.findByName(cr.getName())
                        .orElse(null);
                if(category == null) {
                    throw new CategoryNotFoundException("Category with name " + cr.getName() + " not found");
                } else {
                    product.setCategory(category);
                }
            }
            return this.productRepository.save(product);
        } else{
            throw new CategoryMissingInProductException("Category is required, It cannot be empty");
        }
    }

    @Override
    public Product updateProduct(Product product) {
        return this.saveProduct(product);
    }

    @Override
    public void deleteProduct(String id) {
        this.productRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Product partialProduct(String id, Product product) {
        Product pr = this.productRepository.findById(UUID.fromString(id))
                .orElse(null);
        if(pr == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found", id);
        } else {
            Category cr = product.getCategory();
            if(product.getName() != null) {
                pr.setName(product.getName());
            }
            if(product.getDescription() != null) {
                pr.setDescription(product.getDescription());
            }
            if(product.getPrice() != null) {
                pr.setPrice(product.getPrice());
            }
            if(cr != null) {
                if(cr.getId() == null) {
                    throw new CategoryMissingInProductException("Category ID is missing in JSON for Update Purpose, if you do not want to update category, please remove it from JSON");
                }
                pr.setCategory(cr);
            }
            if(product.getImageUrl() != null) {
                pr.setImageUrl(product.getImageUrl());
            }
            return this.productRepository.save(pr);
        }
    }
}
