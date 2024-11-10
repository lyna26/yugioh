package com.example.yugioh.exceptions;

public class CantGiveNegativeValueException extends RuntimeException {
    public CantGiveNegativeValueException(String message) {
        super(message);
    }
}
