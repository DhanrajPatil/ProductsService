package com.elitefolk.productsservice.dtos;


import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        Category category = new Category();
        category.setName(this.category);
        product.setName(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        product.setId(this.id);
        product.setCategory(category);
        return product;
    }
}
