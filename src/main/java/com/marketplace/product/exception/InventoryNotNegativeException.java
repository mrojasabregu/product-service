package com.marketplace.product.exception;

public class InventoryNotNegativeException extends RuntimeException {
    public InventoryNotNegativeException(String messege) {
        super(messege);
    }
}
