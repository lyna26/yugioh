package com.example.yugioh.models.deck;

import com.example.yugioh.engines.DataBaseEngine;
import com.example.yugioh.models.card.CardImpl;
import lombok.Value;

import java.sql.SQLException;
import java.util.List;

@Value
public class DeckBuilderModel {
    DeckSet deck;

    public DeckBuilderModel(DeckSet deck) {
        this.deck = deck;
    }

    public List<CardImpl> searchCardsByName(String cardName) throws SQLException {
        return DataBaseEngine.selectCardsByName(cardName);
    }

}
