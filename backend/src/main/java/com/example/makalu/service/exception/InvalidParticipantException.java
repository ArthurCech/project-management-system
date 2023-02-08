package com.example.makalu.service.exception;

public class InvalidParticipantException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidParticipantException(String message) {
        super(message);
    }

}
