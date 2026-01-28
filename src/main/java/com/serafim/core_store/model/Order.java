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
    private Integer total_price;
    private OrderStatusEnum status;

    @ManyToOne()
    private User user;

    @OneToMany()
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(Integer total_price, String status, User user) {
        this.total_price = total_price;
        this.status = OrderStatusEnum.valueOf(status);
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Integer getTotal_price() {
        return total_price;
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
}
