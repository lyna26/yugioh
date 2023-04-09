package com.example.yugioh.factory.cardFactory;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.FusionMonster;
import com.example.yugioh.models.card.LinkMonster;
import com.example.yugioh.models.card.NormalMonster;
import com.example.yugioh.models.card.PendulumMonster;
import com.example.yugioh.models.card.RitualMonster;
import com.example.yugioh.models.card.SynchroMonster;
import com.example.yugioh.models.card.XyzMonster;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A factory class for creating MonsterCard objects.
 */
public class MonsterCardFactory implements ICardFactory {

    /**
     * Creates a new MonsterCard object using the provided ResultSet.
     *
     * @param card the ResultSet containing the card data
     * @return a new MonsterCard object
     * @throws SQLException if there is an error retrieving the data from the ResultSet
     */
    @Override
    public Card createCard(ResultSet card) throws SQLException {
        String type = card.getString("type").split(" ")[0].toLowerCase();

        switch (type.toLowerCase()) {
            case "link" -> {
                return new LinkMonster(card);
            }
            case "fusion" -> {
                return new FusionMonster(card);
            }
            case "Pendulum" -> {
                return new PendulumMonster(card);
            }
            case "Ritual" -> {
                return new RitualMonster(card);
            }
            case "Synchro" -> {
                return new SynchroMonster(card);
            }
            case "Xyz" -> {
                return new XyzMonster(card);
            }
            default -> {
                return new NormalMonster(card);
            }
        }
    }
}
