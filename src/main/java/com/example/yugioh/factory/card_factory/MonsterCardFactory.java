package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.CardImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A factory class for creating MonsterCard objects.
 */
public class MonsterCardFactory implements CardFactory{

    @Override
    public CardImpl createCard(ResultSet card) throws SQLException {
        String type = card.getString("type").split(" ")[0].toLowerCase();

        switch (type.toLowerCase()) {
            case "link" -> {
                return new LinkMonster(card);
            }
            case "fusion" -> {
                return new FusionMonster(card);
            }
            case "pendulum" -> {
                return new PendulumMonster(card);
            }
            case "ritual" -> {
                return new RitualMonster(card);
            }
            case "synchro" -> {
                return new SynchroMonster(card);
            }
            case "xyz" -> {
                return new XyzMonster(card);
            }
            default -> {
                return new NormalMonster(card);
            }
        }
    }
}
