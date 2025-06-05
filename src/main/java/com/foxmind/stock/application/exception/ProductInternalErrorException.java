package com.foxmind.stock.application.exception;

public class ProductInternalErrorException extends RuntimeException {
    public ProductInternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
