package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CardFactory {
    Card createCard(ResultSet card);
}
