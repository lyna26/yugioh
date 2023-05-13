package com.example.yugioh.models.field;

import com.example.yugioh.enums.CardType;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.SpellTrapCard;

public class SpellTrapZone extends Zone{
    private final int limitNbCard = 5;

    public SpellTrapZone() {
        super();
    }

    @Override
    public void addCard(int index, Card card) {
        if (getCards().size() < limitNbCard &&
                (card.getTypes().contains(CardType.SPELL.getCardType()) || card.getTypes().contains(CardType.TRAP.getCardType()))) {
            super.addCard(index, card);
        }
    }
}
