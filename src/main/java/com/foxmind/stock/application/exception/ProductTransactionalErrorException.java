package com.foxmind.stock.application.exception;

public class ProductTransactionalErrorException extends RuntimeException {
    public ProductTransactionalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
