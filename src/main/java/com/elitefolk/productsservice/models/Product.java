package com.elitefolk.productsservice.models;

import com.elitefolk.productsservice.dtos.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@Table( indexes = { @Index(name = "product_category_index", columnList = "category_id"),
                   @Index(name = "product_name_index", columnList = "name")})
@SqlResultSetMapping(
        name = "ProductDtoMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ProductDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = UUID.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "price", type = Double.class),
                                @ColumnResult(name = "imageUrl", type = String.class),
                                @ColumnResult(name = "categoryName", type = String.class),
                                @ColumnResult(name = "categoryId", type = UUID.class)
                        }
                )
        }
)
@NamedStoredProcedureQuery(
        name = "Product.getAllProductWithCategoryDetails",
        procedureName = "get_all_product_with_category_details",
        parameters = {
                @StoredProcedureParameter(name = "pageSize", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "pageNo", type = Integer.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "sortColumn", type = String.class, mode = ParameterMode.IN),
                @StoredProcedureParameter(name = "sortDirection", type = String.class, mode = ParameterMode.IN)
        },
        resultSetMappings = {"ProductDtoMapping"}
)

@NamedNativeQuery(
        name = "fetch_products_by_deleted_false",
        query = "SELECT p.id as id, p.name as name, p.description as description, " +
                "p.price as price, p.image_url as imageUrl, " +
                "c.name as categoryName, c.id as categoryId " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id " +
                "WHERE p.is_deleted = false " +
                "order by :sortField " +
                "LIMIT :pageSize OFFSET :pageNo",
        resultSetMapping = "ProductDtoMapping"
)
public class Product extends BaseModel{
    @Column(nullable = false, length = 150)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 150)
    private String imageUrl;

    @JsonIgnoreProperties({ "products" })
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Category category;
}
