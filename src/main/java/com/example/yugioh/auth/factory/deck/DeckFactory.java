package com.example.yugioh.auth.factory.deck;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.entity.deck.Deck;

public interface DeckFactory {
    CardDTO createDeck(Deck card);
}
