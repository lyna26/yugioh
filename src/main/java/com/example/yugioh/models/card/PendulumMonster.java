package com.example.yugioh.models.card;

import javafx.scene.effect.Effect;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * A class representing a pendulum monster card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link MonsterCard} class.
 */
@ToString(includeFieldNames = true)
@Getter
@Setter
public class PendulumMonster extends MonsterCard {
    private final int scale;
    private List<Effect> monsterEffect;
    private List<Effect> spellEffect;

    public PendulumMonster(ResultSet card) throws SQLException {
        super(card);
        this.scale = card.getInt("scale");
    }
}
