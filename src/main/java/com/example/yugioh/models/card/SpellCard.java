package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing a spell card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link CardImpl} class.
 */
@ToString()
@Getter
@Setter
public class SpellCard extends SpellTrapCard {
    public SpellCard(ResultSet card) throws SQLException {
        super(card);
    }
}
