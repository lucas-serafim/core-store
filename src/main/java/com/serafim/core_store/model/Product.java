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
        updateName(name);
        updateQuantity(quantity);
        updatePrice(price);
        updateDescription(description);

        this.category = category;
    }

    public void updateName(String name) {
        if (name.length() < 3 || name.length() > 150) {
            throw new IllegalArgumentException("Invalid name.");
        }

        this.name = name;
    }

    public void updateQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity should be positive");
        }

        this.quantity = quantity;
    }

    public void updatePrice(Integer price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }

        this.price = price;
    }

    public void updateDescription(String description) {
        if (description.isEmpty()) {
            description = "no description";
        }

        this.description = description;
    }

    public void decreaseQuantity(Integer value) {
        int total = this.quantity -= value;

        if (total < 0) {
            throw new IllegalArgumentException("Product out of stock.");
        }

        this.quantity = total;
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
