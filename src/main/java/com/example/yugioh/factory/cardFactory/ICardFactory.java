package com.example.yugioh.factory.cardFactory;

import com.example.yugioh.models.card.Card;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ICardFactory {
    Card createCard(ResultSet card) throws SQLException;
}
