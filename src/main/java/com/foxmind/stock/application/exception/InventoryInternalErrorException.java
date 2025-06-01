package com.foxmind.stock.application.exception;

public class InventoryInternalErrorException extends RuntimeException {
    public InventoryInternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
