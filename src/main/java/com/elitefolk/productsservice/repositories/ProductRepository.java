package com.elitefolk.productsservice.repositories;

import com.elitefolk.productsservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Override
    Optional<Product> findById(UUID uuid);

    @Override
    List<Product> findAll();

    List<Product> findByIdIn(List<UUID> ids);

    List<Product> findByCategoryName(String categoryName);

    @Override
    <S extends Product> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Override
    void delete(Product entity);
}
