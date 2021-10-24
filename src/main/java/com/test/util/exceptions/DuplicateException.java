package com.test.util.exceptions;

public class DuplicateException extends Exception {
    public DuplicateException() {
    }

    public DuplicateException(String message) {
        super(message);
    }
}
