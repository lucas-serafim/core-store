package com.serafim.core_store.exception;

public class SignInException extends RuntimeException {
    public SignInException() {
        super("Username or password is wrong.");
    }
}
