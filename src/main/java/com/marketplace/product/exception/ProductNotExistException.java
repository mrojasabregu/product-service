package com.marketplace.product.exception;

public class ProductNotExistException extends RuntimeException {
    public ProductNotExistException(String messege) {
        super(messege);
    }
}
