package com.example.yugioh.models.card;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SpellTrapCard extends Card {

    /**
     * Constructor used to generate a card from data exported from a database.
     * @param card ResultSet containing card information.
     */
    public SpellTrapCard(ResultSet card) throws SQLException {
        super(card);
    }
}
