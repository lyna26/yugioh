package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing a trap card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link Card} class.
 */
@ToString(includeFieldNames = true)
@Getter
@Setter
public class TrapCard extends SpellTrapCard {
    public TrapCard(ResultSet card) throws SQLException {
        super(card);
    }
}
