package com.marketplace.product.exception;

public class ProductExistException extends RuntimeException {
    public ProductExistException(String messege) {
        super(messege);
    }

}
