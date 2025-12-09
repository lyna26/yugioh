package com.example.yugioh.auth.factory.deck;

import com.example.yugioh.auth.dto.deck.DeckDTO;
import com.example.yugioh.auth.entity.deck.Deck;

public interface DeckFactory {
    DeckDTO createDeck(Deck deck);
}
