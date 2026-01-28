package com.serafim.core_store.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity()
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private Integer quantity;
    private Integer price;
    private String description;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String name, Integer quantity, Integer price, String description, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }
}
