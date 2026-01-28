package com.serafim.core_store.model;

public enum OrderStatusEnum {
    PENDING("pending"),
    PAID("paid"),
    SHIPPED("shipped");

    private String status;

    OrderStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
