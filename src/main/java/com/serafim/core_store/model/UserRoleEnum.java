package com.serafim.core_store.model;

public enum UserRoleEnum {
    CUSTOMER("customer"),
    ADMIN("admin");

    private String role;

    UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
