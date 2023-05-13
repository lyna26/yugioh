package com.example.yugioh.models.field;

import com.example.yugioh.enums.CardType;
import com.example.yugioh.models.card.Card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

@Getter
@Setter
@ToString
public class MonsterZone extends Zone{
   int limitNbCard = 5;
   CardType cardType = CardType.MONSTER;

    public MonsterZone() {
        super();
    }

    @Override
    public void addCard(int index, Card card) {
        super.addCard(index, card);
    }
}
