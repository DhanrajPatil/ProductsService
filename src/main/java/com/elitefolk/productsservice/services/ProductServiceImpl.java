package com.elitefolk.productsservice.services;

import com.elitefolk.productsservice.dtos.ProductDto;
import com.elitefolk.productsservice.exceptions.CategoryMissingInProductException;
import com.elitefolk.productsservice.exceptions.CategoryNotFoundException;
import com.elitefolk.productsservice.exceptions.ProductNotFoundException;
import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import com.elitefolk.productsservice.repositories.CategoryRepository;
import com.elitefolk.productsservice.repositories.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Product> getProducts(Pageable pageable) {
        int defaultPage = pageable.getPageNumber();
        int minSize = pageable.getPageSize();
        if(pageable.getSort().isEmpty()) {
            return this.productRepository.findByIsDeletedFalse(
                    PageRequest.of(defaultPage, minSize, Sort.by("name").ascending())
            );
        }
        Sort sort;
        String defaultSortBy = pageable.getSort().toString().split(":")[0];
        String sortDirection = pageable.getSort().toString().split(":")[1];
        if(sortDirection.equals("DESC")) {
            sort = Sort.by(defaultSortBy).descending();
        } else {
            sort = Sort.by(defaultSortBy).ascending();
        }
        Sort.by(defaultSortBy).ascending();
        return this.productRepository.findByIsDeletedFalse(
                PageRequest.of(defaultPage, minSize, sort)
        );
    }

    @Override
    @Transactional
    public Page<ProductDto> getProductsUsingProcedure(Pageable pageable) {
        List<ProductDto> dtos = this.productRepository.getAllProductWithCategories(
                pageable.getPageSize(),
                pageable.getPageNumber()
        );
        Long totalRecords = this.productRepository.count();
        return new PageImpl<>(dtos, pageable, totalRecords);
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
