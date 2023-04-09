package com.example.yugioh.factory.cardFactory;

import com.example.yugioh.models.card.Card;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The CardFactory class represents a factory that creates cards based on the provided card data.
 * It uses a map of card factories for each card type, and creates a card using the corresponding factory.
 */
public class CardFactory {
    private static final Map<String, ICardFactory> factories = Map.of(
            "monster", new MonsterCardFactory(),
            "spell", new SpellCardFactory(),
            "trap", new TrapCardFactory()
    );


    /**
     * Creates a new card based on the provided card data.
     *
     * @param card The ResultSet object that contains the card data.
     * @return A Card object.
     * @throws SQLException             If there is an error reading the data from the ResultSet
     * @throws IllegalArgumentException If the provided card type is unknown.
     */
    public static Card createCard(ResultSet card) throws SQLException {
        String type = card.getString("type").split(" ")[0].toLowerCase();
        ICardFactory factory = switch (type.toLowerCase()) {
            case "spell" -> factories.get("spell");
            case "trap" -> factories.get("trap");
            default -> factories.get("monster");
        };
        return factory.createCard(card);
    }
}
