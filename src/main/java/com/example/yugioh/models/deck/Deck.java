package com.example.yugioh.models.deck;

import com.example.yugioh.models.card.Card;

public interface Deck {
    void addCard(Card card) ;
    void removeCard(Card card) ;
    void shuffle() ;
    int getMinCard () ;
    int getMaxCard () ;
    String getType () ;
    boolean isValidDeck();
}
