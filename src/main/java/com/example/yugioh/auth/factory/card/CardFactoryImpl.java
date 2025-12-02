package com.example.yugioh.auth.factory.card;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.entity.Card;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * The CardFactoryImpl class represents a factory that creates cards based on the provided card data.
 */

@Slf4j
@UtilityClass
public class CardFactoryImpl {
    private static final Map<String, CardFactory> FACTORIES = new HashMap<>();

    static {
        FACTORIES.put("monster", new MonsterCardFactory());
        FACTORIES.put("spell", new SpellCardFactory());
        FACTORIES.put("trap", new TrapCardFactory());
    }

    public static CardDTO createCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card cannot be null");
        }

        final String cardFrameType = card.getFrameType();

        CardFactory factory = FACTORIES.getOrDefault(cardFrameType, new MonsterCardFactory());
        return factory.createCard(card);
    }
}
