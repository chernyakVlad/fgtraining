package com.training.SpringBootTask.controllers.exceptions;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserValidationException(String msg) {
        super(msg);
    }
}

