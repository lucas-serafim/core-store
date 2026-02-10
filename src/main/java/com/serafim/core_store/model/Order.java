package com.serafim.core_store.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public Order() {
    }

    // TODO: Add user
    public Order(Integer totalPrice, OrderStatusEnum status) {
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public void cancel() {
        this.status = OrderStatusEnum.CANCELLED;
    }

    public void removeOrderItemById(UUID itemId) {
        boolean isRemoved = this.orderItems.removeIf(i -> i.getId().equals(itemId));

        if (!isRemoved) {
            throw new IllegalArgumentException("Order item not found. id: " + itemId);
        }

        this.updateTotalPrice();
    }

    private void updateTotalPrice() {
        this.totalPrice = this.orderItems.stream().mapToInt(OrderItem::getPriceAtPurchase).sum();
    }

    public UUID getId() {
        return id;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        orderItems.forEach(i -> i.setOrder(this));
        this.orderItems = orderItems;
    }
}
