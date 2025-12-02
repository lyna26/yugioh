package com.example.yugioh.game.exceptions;

public class CantGiveNegativeValueException extends RuntimeException {
    public CantGiveNegativeValueException(String message) {
        super(message);
    }
}
