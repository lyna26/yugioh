package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.card.TrapCard;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A factory class for creating TrapCard objects.
 */
public class TrapCardFactory implements CardFactory {
    @Override
    public CardImpl createCard(ResultSet card) throws SQLException {
        return new TrapCard(card);
    }
}
