package com.example.yugioh.exceptions;

public class UnknownCardTypeException extends RuntimeException {
    public UnknownCardTypeException(String message) {
        super(message);
    }
}
