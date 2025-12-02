package com.example.yugioh.game.exceptions;

public class CardCantHandleActionException extends RuntimeException {
    public CardCantHandleActionException(String message) {
        super(message);
    }
}
