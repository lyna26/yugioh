package com.example.yugioh.exceptions;

import java.sql.SQLException;

public class CardInitializationException extends RuntimeException{
    public CardInitializationException(String message, Exception exception) {
        super(message, exception);
    }
}
