package com.foxmind.stock.application.exception;

public class InventoryInternalErrorException extends RuntimeException {
    public InventoryInternalErrorException(String message, Throwable error) {
        super(message, error);
    }
}
