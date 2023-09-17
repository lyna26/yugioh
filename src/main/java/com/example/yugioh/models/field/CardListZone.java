package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;

import java.util.ArrayList;
import java.util.List;

public class CardListZone extends ZoneImpl {

    private List<Card> cards;

    public CardListZone() {
        cards = new ArrayList<>();
    }

    @Override
    public void addCard(CardImpl card) {
        cards.add(card);
    }

    @Override
    public void removeCard(CardImpl card) {
        cards.remove(card);
    }

}
