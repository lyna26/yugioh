package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;

import java.io.Serializable;

/**
 * This class represents a set of decks used in the game of Yu-Gi-Oh!.
 * Each deck set contains a main deck, an extra deck, and a side deck.
 */
public class DeckSet implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Deck mainDeck;
    private Deck extraDeck;
    private Deck sideDeck;

    /**
     * Creates a new deck set with the given name.
     * Initializes the main, extra, and side decks with new Deck objects.
     *
     * @param name The name of the deck set.
     */
    public DeckSet(String name) {
        this.name = name;
        mainDeck = new Deck(DeckType.MAIN);
        extraDeck = new Deck(DeckType.EXTRA);
        sideDeck = new Deck(DeckType.SIDE);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public Deck getExtraDeck() {
        return extraDeck;
    }

    public void setExtraDeck(Deck extraDeck) {
        this.extraDeck = extraDeck;
    }

    public Deck getSideDeck() {
        return sideDeck;
    }

    public void setSideDeck(Deck sideDeck) {
        this.sideDeck = sideDeck;
    }
}
