package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.CardImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CardFactory {
    CardImpl createCard(ResultSet card) throws SQLException;
}
