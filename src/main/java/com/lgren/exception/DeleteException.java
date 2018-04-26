package com.lgren.exception;

public class DeleteException extends RuntimeException {
    public DeleteException() {
        super();
    }
    public DeleteException(String message) {
        super(message);
    }
}
