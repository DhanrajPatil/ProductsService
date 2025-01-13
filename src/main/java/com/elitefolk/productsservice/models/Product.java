package com.elitefolk.productsservice.models;

import com.elitefolk.productsservice.dtos.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@Table(indexes = { @Index(name = "product_category_index", columnList = "category_id"),
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
                @StoredProcedureParameter(name = "pageNo", type = Integer.class, mode = ParameterMode.IN)
        },
        resultSetMappings = {"ProductDtoMapping"}
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
    private Category category;
}
