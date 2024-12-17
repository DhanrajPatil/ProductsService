package com.elitefolk.productsservice.dtos;


import com.elitefolk.productsservice.models.Category;
import com.elitefolk.productsservice.models.Product;

public class FakeStoreProductDto {
    Long id;
    String title;
    Double price;
    String category;
    String description;
    String image;

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

    public FakeStoreProductDto() {
    }

    public FakeStoreProductDto(Long id, String title, Double price, String category, String description, String image) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
