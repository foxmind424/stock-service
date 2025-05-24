package com.foxmind.stock.application.exception;

public class InventoryTransactionErrorException extends RuntimeException {
    public InventoryTransactionErrorException(String message, Throwable error) {
        super(message, error);
    }
}    