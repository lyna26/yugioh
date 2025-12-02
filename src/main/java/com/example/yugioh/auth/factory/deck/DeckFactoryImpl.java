package com.example.yugioh.auth.factory.deck;

import com.example.yugioh.auth.dto.deck.DeckDTO;
import com.example.yugioh.auth.entity.deck.Deck;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * The CardFactoryImpl class represents a factory that creates cards based on the provided card data.
 */

@Slf4j
@UtilityClass
public class DeckFactoryImpl {
    private static final Map<String, DeckFactory> FACTORIES = new HashMap<>();

    public static DeckDTO createCard(Deck deck) {
        return null;

    }
}
