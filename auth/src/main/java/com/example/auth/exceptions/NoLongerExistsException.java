package com.example.auth.exceptions;

public class NoLongerExistsException extends RuntimeException {
    public NoLongerExistsException(String message) {
        super(message);
    }
}
