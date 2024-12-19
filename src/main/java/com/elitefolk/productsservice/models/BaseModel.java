package com.elitefolk.productsservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {
    private Long id;
    private Long createdDate;
    private Long updatedDate;
    private Boolean isDeleted;
}
