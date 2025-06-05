package com.foxmind.stock.application.exception;

public class CategoryNotFoundErrorException extends RuntimeException {
    public CategoryNotFoundErrorException(String message) {
        super(message);
    }
}
