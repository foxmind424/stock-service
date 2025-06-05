package com.foxmind.stock.application.exception;

public class CategoryTransactionalErrorException extends RuntimeException {
    public CategoryTransactionalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
