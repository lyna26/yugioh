package com.example.yugioh.game.exceptions;

public class CantRemoveCardFromDeckException extends RuntimeException{
    public CantRemoveCardFromDeckException(String message) {
        super(message);
    }
}
