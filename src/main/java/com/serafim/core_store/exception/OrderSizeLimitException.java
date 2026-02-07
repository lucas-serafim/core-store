package com.serafim.core_store.exception;

public class OrderSizeLimitException extends RuntimeException {
  public OrderSizeLimitException(String message) {
    super(message);
  }
}
