package com.example.makalu.service.exception;

public class DatabaseIntegrityViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatabaseIntegrityViolationException(String message) {
        super(message);
    }

}
