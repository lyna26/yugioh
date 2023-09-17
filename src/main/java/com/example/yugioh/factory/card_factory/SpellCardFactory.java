package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.card.SpellCard;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A factory class for creating SpellCard objects.
 */
public class SpellCardFactory implements CardFactory {
    @Override
    public CardImpl createCard(ResultSet card) throws SQLException {
        return new SpellCard(card);
    }
}
