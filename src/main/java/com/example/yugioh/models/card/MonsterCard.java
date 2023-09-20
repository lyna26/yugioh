package com.example.yugioh.models.card;

import com.example.yugioh.enums.Position;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing a monster card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link CardImpl} class.
 */
@ToString()
@Getter
@Setter
public abstract class MonsterCard extends CardImpl {
    private int atk;
    private int def;
    private int level;
    private String attribute;
    private Position position;

    /**
     * Constructs a new MonsterCard object with the given data from a database ResultSet.
     * @param card a ResultSet object containing the card data
     */
    protected MonsterCard(ResultSet card) throws SQLException {
        super(card);
        this.atk = card.getInt("atk");
        this.def = card.getInt("def");
        this.level = card.getInt("level");
        this.attribute = card.getString("attribute");
        this.position = Position.ATK;
    }
}