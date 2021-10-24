package com.test.util.exceptions;

public class DuplicateVerificationCode extends Exception {
    public DuplicateVerificationCode() {
    }

    public DuplicateVerificationCode(String message) {
        super(message);
    }

    public DuplicateVerificationCode(String message, Throwable cause) {
        super(message, cause);
    }
}
