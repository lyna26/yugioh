package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing a ritual monster card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link MonsterCard} class.
 */
@ToString(includeFieldNames = true)
@Getter
@Setter
public class RitualMonster extends MonsterCard {
    public RitualMonster(ResultSet card) throws SQLException {
        super(card);
    }
}
