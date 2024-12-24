package com.elitefolk.productsservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
@Table(indexes = { @Index(name = "category_name_index", columnList = "name")})
public class Category extends BaseModel{
    @Column(nullable = false, unique = true)
    private String name;
}
