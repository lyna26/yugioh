package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.Card;
import lombok.extern.slf4j.Slf4j;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */

@Slf4j
public class DeckSide extends DeckImpl {
    public DeckSide() {
        super(DeckType.SIDE);
    }


    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }
}