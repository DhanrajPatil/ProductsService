package com.elitefolk.productsservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
@Table(indexes = { @Index(name = "product_category_index", columnList = "category_id"),
                   @Index(name = "product_name_index", columnList = "name")})
public class Product extends BaseModel{
    @Column(nullable = false, length = 150)
    private String name;

    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String imageUrl;

    @JsonIgnoreProperties({ "products" })
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
