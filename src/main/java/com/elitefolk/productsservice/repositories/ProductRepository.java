package com.elitefolk.productsservice.repositories;

import com.elitefolk.productsservice.dtos.ProductDto;
import com.elitefolk.productsservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

    @Override
    Optional<Product> findById(UUID uuid);

    @Override
    List<Product> findAll();

    Page<Product> findByIsDeletedFalse(Pageable pageable);

    List<Product> findByIsDeletedFalse();

    List<Product> findByIdIn(List<UUID> ids);

    List<Product> findByCategoryNameIgnoreCase(String categoryName);

    List<Product> findByNameContains(String name);

    @Override
    <S extends Product> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Override
    void delete(Product entity);

    @Procedure(name = "Product.getAllProductWithCategoryDetails")
    List<ProductDto> getAllProductWithCategories(@Param("pageSize") Integer size,
                                                 @Param("pageNo") Integer pageNo);
}
