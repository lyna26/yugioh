package com.example.yugioh.auth.factory.card;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.dto.card.SpellCardImpl;
import com.example.yugioh.auth.entity.Card;

public class SpellCardFactory implements CardFactory {

    @Override
    public CardDTO createCard(Card card) {
        if (card == null) throw new IllegalArgumentException("Card data cannot be null");
        return new SpellCardImpl(card);
    }
}
