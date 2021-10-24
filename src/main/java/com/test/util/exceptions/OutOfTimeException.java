package com.test.util.exceptions;

public class OutOfTimeException extends  Exception{
    public OutOfTimeException() {
    }

    public OutOfTimeException(String message) {
        super(message);
    }

    public OutOfTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
