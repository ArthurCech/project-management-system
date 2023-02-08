package com.example.makalu.service.exception;

public class DomainNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainNotFoundException(String message) {
        super(message);
    }

}
