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
    String name;
    DeckImpl mainDeck;
    DeckImpl extraDeck;
    DeckImpl sideDeck;

    public DeckSet(String name) {
        this.name = name;
        mainDeck = new DeckMain();
        extraDeck = new DeckExtra();
        sideDeck = new DeckSide();
    }
}