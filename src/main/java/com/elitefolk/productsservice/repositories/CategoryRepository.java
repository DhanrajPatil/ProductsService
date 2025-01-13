package com.elitefolk.productsservice.repositories;

import com.elitefolk.productsservice.dtos.CategoriesUsingProcedureDto;
import com.elitefolk.productsservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<Category> findAllByIsDeleted(boolean isDeleted);

    @Override
    List<Category> findAll();

    @Override
    Optional<Category> findById(UUID uuid);

    List<Category> findByIdIn(List<UUID> ids);

    Optional<Category> findByName(String categoryName);

    List<Category> findByNameContains(String containingName);

    @Override
    <S extends Category> S save(S entity);

    @Override
    void deleteById(UUID uuid);

    @Procedure(name="Category.getAllCategoriesWithProducts")
    List<CategoriesUsingProcedureDto> getAllCategoriesUsingProcedure();
}
