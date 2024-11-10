package com.example.yugioh.exceptions;

public class CardCantHandleActionException extends RuntimeException {
    public CardCantHandleActionException(String message) {
        super(message);
    }
}
