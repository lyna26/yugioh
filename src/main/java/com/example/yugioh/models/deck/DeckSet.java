package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents a complete deck ( main, side & extra) in the game of Yu-Gi-Oh!.
 */

@Getter
@Setter
public class DeckSet implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    String name;
    Deck mainDeck;
    Deck extraDeck;
    Deck sideDeck;

    public DeckSet(String name) {
        this.name = name;
        mainDeck = new Deck(DeckType.MAIN);
        extraDeck = new Deck(DeckType.EXTRA);
        sideDeck = new Deck(DeckType.SIDE);
    }
}