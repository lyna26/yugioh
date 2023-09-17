package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents a complete deck ( main, side & extra) in the game of Yu-Gi-Oh!.
 */

@Getter
@Setter
public class DeckSet implements Serializable {

    @Serial private static final long serialVersionUID = 1L;
    private String name;
    private Deck mainDeck;
    private Deck extraDeck;
    private Deck sideDeck;

    public DeckSet(String name) {
        this.name = name;
        mainDeck = new Deck(DeckType.MAIN);
        extraDeck = new Deck(DeckType.EXTRA);
        sideDeck = new Deck(DeckType.SIDE);
    }

    public void updateAllDecks(Deck mainDeck, Deck extraDeck, Deck sideDeck) {
        setMainDeck(mainDeck);
        setExtraDeck(extraDeck);
        setSideDeck(sideDeck);
    }
}