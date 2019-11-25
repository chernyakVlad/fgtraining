package com.training.SpringBootTask.controllers;

import com.training.SpringBootTask.controllers.exceptions.UserValidationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ExceptionEntity> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserValidationException.class)
    protected ResponseEntity<ExceptionEntity> handleUserValidationException(final UserValidationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    static class ExceptionEntity {
        private String message;

        public ExceptionEntity(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
