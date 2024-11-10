package com.example.yugioh.factory.card_factory;

import com.example.yugioh.models.card.Card;

import java.sql.ResultSet;
import java.util.Map;

/**
 * The CardFactoryImpl class represents a factory that creates cards based on the provided card data.
 */

public class CardFactoryImpl {
    private CardFactoryImpl() {
    }
    private static final Map<String, CardFactory> factories = Map.of(
            "monster", new MonsterCardFactory()
    );

    private static CardFactory getInstance(String type) {
        return factories.getOrDefault(type, new MonsterCardFactory());
    }

    public static Card createCard(ResultSet card) {
        CardFactory factory = factories.get("monster");
                return factory.createCard(card);
            }
}
