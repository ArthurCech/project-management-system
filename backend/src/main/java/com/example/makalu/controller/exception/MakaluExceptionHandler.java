package com.example.makalu.controller.exception;

import com.example.makalu.service.exception.DatabaseIntegrityViolationException;
import com.example.makalu.service.exception.DomainNotFoundException;
import com.example.makalu.service.exception.InvalidParticipantException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class MakaluExceptionHandler {

    @ExceptionHandler(DomainNotFoundException.class)
    public ResponseEntity<StandardError> handleDomainNotFoundException(DomainNotFoundException e,
                                                                       HttpServletRequest req) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                req.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseIntegrityViolationException.class)
    public ResponseEntity<StandardError> handleDatabaseIntegrityViolationException(DatabaseIntegrityViolationException e,
                                                                                   HttpServletRequest req) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                req.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                                 HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError error = new ValidationError(
                Instant.now(),
                status.value(),
                "Validation Error",
                e.getMessage(),
                request.getRequestURI());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(InvalidParticipantException.class)
    public ResponseEntity<StandardError> handleInvalidParticipantException(InvalidParticipantException e,
                                                                           HttpServletRequest req) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                req.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

}
