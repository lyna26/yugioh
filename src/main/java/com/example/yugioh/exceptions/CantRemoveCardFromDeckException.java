package com.example.yugioh.exceptions;

public class CantRemoveCardFromDeckException extends RuntimeException{
    public CantRemoveCardFromDeckException(String message) {
        super(message);
    }
}
