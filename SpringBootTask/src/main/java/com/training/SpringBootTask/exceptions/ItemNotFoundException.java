package com.training.SpringBootTask.exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
