package com.elitefolk.productsservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
@Table(indexes = { @Index(name = "category_name_index", columnList = "name")})
public class Category extends BaseModel{
    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnoreProperties({ "category" })
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Product> products;
}
