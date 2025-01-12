package com.elitefolk.productsservice.models;

import com.elitefolk.productsservice.dtos.CategoriesUsingProcedureDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
@Table(indexes = { @Index(name = "category_name_index", columnList = "name")})
@SqlResultSetMapping(
        name = "GetCategoriesWithProductsMapping",
        classes = {
                @ConstructorResult(
                        targetClass = CategoriesUsingProcedureDto.class,
                        columns = {
                                @ColumnResult(name = "categoryId", type = UUID.class),
                                @ColumnResult(name = "categoryName", type = String.class),
                                @ColumnResult(name = "productId", type = UUID.class),
                                @ColumnResult(name = "productName", type = String.class),
                                @ColumnResult(name = "productDescription", type = String.class),
                                @ColumnResult(name = "price", type = Double.class),
                                @ColumnResult(name = "imageUrl", type = String.class)
                        }
                )
        }
)
@NamedStoredProcedureQuery(
        name = "Category.getAllCategoriesWithProducts",
        procedureName = "get_all_categories_with_products",
        resultSetMappings = "GetCategoriesWithProductsMapping"
)
public class Category extends BaseModel{
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @JsonIgnoreProperties({ "category" })
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Product> products;
}
