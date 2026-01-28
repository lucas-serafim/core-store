package com.serafim.core_store.exception;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException() {
        super("Category already exists");
    }
}
