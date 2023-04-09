package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;
import lombok.Value;

@Value
public class HandZone extends Zone {
    int limitNbCard = 6;

    public HandZone() {
        super();
    }

    @Override
    public void addCard(Card card) {
        if (getCards().size() < limitNbCard) {
            super.addCard(card);
        }
    }
}
