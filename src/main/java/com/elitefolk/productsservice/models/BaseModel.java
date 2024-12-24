package com.elitefolk.productsservice.models;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EnableJpaAuditing
public class BaseModel {
    @Id
    private UUID id;
    @CreationTimestamp
    private Long createdDate;
    @UpdateTimestamp
    private Long updatedDate;
    private Boolean isDeleted = false;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UuidCreator.getTimeOrdered();
        }
    }
}
