package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.CardImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The CardFactoryImpl class represents a factory that creates cards based on the provided card data.
 */

public class CardFactoryImpl{
    private CardFactoryImpl() {}
    private static final Map<String, CardFactory> factories = Map.of(
            "monster", new MonsterCardFactory(),
            "spell", new SpellCardFactory(),
            "trap", new TrapCardFactory()
    );

    private static CardFactory getInstance(String type) {
        return factories.getOrDefault(type, new MonsterCardFactory());
    }

    public static CardImpl createCard(ResultSet card) throws SQLException {
        String type = card.getString("type").split(" ")[0].toLowerCase();
        CardFactory factory = getInstance(type);
        return factory.createCard(card);
    }
}
