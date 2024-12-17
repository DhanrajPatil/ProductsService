package com.elitefolk.productsservice.models;

public class Product extends BaseModel{
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

    public Product(Long id, String createdBy, String updatedBy, Long createdDate, Long updatedDate, Boolean isDeleted, String name, String description, Double price, String imageUrl, Category category) {
        super(id, createdBy, updatedBy, createdDate, updatedDate, isDeleted);
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
