package com.elitefolk.productsservice.models;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Where;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "categories")
public class Category extends BaseModel{
    private String name;
}
