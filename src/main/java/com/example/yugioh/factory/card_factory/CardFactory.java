package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.Card;

import java.sql.ResultSet;

public interface CardFactory {
    Card createCard(ResultSet card);
}
