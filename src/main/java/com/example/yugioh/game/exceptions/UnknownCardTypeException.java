package com.example.yugioh.game.exceptions;

public class UnknownCardTypeException extends RuntimeException {
    public UnknownCardTypeException(String message) {
        super(message);
    }
}
