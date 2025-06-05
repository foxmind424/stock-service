package com.foxmind.stock.application.exception;

public class CategoryInternalErrorException extends RuntimeException {
    public CategoryInternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
