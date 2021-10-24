package com.test.util.exceptions;

public class InsufficientResources extends Exception{
    public InsufficientResources() {
    }

    public InsufficientResources(String message) {
        super(message);
    }

    public InsufficientResources(String message, Throwable cause) {
        super(message, cause);
    }
}
