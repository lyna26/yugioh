package com.example.yugioh.models.card;

import com.example.yugioh.enums.Position;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public abstract class MonsterCard extends CardImpl implements LevelCard {
    private int atk;
    private int def;
    private int level;
    private String attribute;
    private Position position;

    public MonsterCard(ResultSet card) throws SQLException {
        super(card);
        this.atk = card.getInt("atk");
        this.def = card.getInt("def");
        this.level = card.getInt("level");
        this.attribute = card.getString("attribute");
        this.position = Position.ATK;  // Default position
    }
}