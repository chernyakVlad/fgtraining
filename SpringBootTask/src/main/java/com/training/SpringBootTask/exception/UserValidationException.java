package com.training.SpringBootTask.exception;

public class UserValidationException extends RuntimeException {
    public UserValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserValidationException(String msg) {
        super(msg);
    }
}

