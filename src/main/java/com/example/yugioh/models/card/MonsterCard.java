package com.example.yugioh.models.card;

import com.example.yugioh.enums.Position;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing a monster card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link Card} class.
 */
@ToString(includeFieldNames = true)
@Getter
@Setter
public abstract class MonsterCard extends Card {
    private int atk;
    private int def;
    private int level;
    private String attribute;
    private Position position = Position.ATK;

    /**
     * Constructs a new MonsterCard object with the given data from a database ResultSet.
     * @param card a ResultSet object containing the card data
     */
    public MonsterCard(ResultSet card) throws SQLException {
        super(card);
        this.atk = card.getInt("atk");
        this.def = card.getInt("def");
        this.level = card.getInt("level");
        this.attribute = card.getString("attribute");
    }
}