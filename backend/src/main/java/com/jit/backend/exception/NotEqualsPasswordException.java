package com.jit.backend.exception;

public class NotEqualsPasswordException extends IllegalStateException{
    public NotEqualsPasswordException(String message) {
        super(message);
    }
}
