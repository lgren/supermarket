package com.lgren.exception;

public class TransactionException extends RuntimeException {
    public TransactionException() {
        super();
    }
    public TransactionException(String message) {
        super(message);
    }
}
