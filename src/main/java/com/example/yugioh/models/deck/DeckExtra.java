package com.example.yugioh.models.deck;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.ExtraDeckMonster;
import lombok.extern.slf4j.Slf4j;

import static com.example.yugioh.enums.DeckType.EXTRA;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */

@Slf4j
public class DeckExtra extends DeckImpl {
    public DeckExtra() {
        super(EXTRA);
    }

    public void addCard(Card card) {
        if (card instanceof ExtraDeckMonster){
            super.addCard(card);
        }else{
            log.warn("cant add card");
        }
    }
}
