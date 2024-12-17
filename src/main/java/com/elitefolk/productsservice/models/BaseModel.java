package com.elitefolk.productsservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel {
    private Long id;
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;
    private Boolean isDeleted;
}
