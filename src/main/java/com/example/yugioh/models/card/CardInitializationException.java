package com.example.yugioh.models.card;

import java.sql.SQLException;

public class CardInitializationException extends RuntimeException{
    public CardInitializationException(String message, SQLException exception) {
        super(message, exception);
    }
}
