package com.foxmind.stock.application.exception;

public class InventoryNotFoundErrorException extends RuntimeException {
    public InventoryNotFoundErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryNotFoundErrorException(String message) {
        super(message);
    }
}
