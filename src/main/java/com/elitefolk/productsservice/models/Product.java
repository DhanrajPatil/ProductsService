package com.elitefolk.productsservice.models;

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
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String imageUrl;
    @ManyToOne
    private Category category;
}
