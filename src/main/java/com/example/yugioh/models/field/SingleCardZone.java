package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;

public class SingleCardZone extends ZoneImpl {
    private CardImpl card;

    public SingleCardZone() {
    }

    /**
     * @param card
     */


    public void setCard(CardImpl card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }


    @Override
    public void addCard(CardImpl card) {
        if (this.card == null) {
            this.card = card;
        }
    }

    @Override
    public void removeCard(CardImpl card) {
        if (this.card == card) {
            this.card = null;
        }
    }
}
