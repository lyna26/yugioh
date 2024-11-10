package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.exceptions.CantRemoveCardFromDeckException;
import com.example.yugioh.exceptions.DeckTypeNullException;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.RitualCardImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.yugioh.enums.DeckType.MAIN;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */

@Slf4j
public class DeckMain extends DeckImpl {
    public DeckMain() {
        super(MAIN);
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }
}
