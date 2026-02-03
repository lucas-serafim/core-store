package com.serafim.core_store.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer quantity;
    private Integer priceAtPurchase;

    @ManyToOne()
    private Order order;

    @ManyToOne()
    private Product product;

    public OrderItem() {
    }

    public OrderItem(Integer quantity, Integer priceAtPurchase, Product product) {
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
