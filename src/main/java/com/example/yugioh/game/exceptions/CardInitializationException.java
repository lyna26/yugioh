package com.example.yugioh.game.exceptions;

import java.sql.SQLException;

public class CardInitializationException extends RuntimeException{
    public CardInitializationException(String message, Exception exception) {
        super(message, exception);
    }
}
