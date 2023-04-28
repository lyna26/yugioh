package com.example.yugioh.models.field;

import com.example.yugioh.enums.CardType;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.MonsterCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonsterZone extends Zone{
   private final int limitNbCard = 5;
   private final CardType cardType = CardType.MONSTER;

   private MonsterCard target;
    public MonsterZone() {
        super();
    }

    @Override
    public void addCard(Card card) {
        if (getCards().size() < limitNbCard && card.getTypes().contains(cardType.getCardType())) {
            System.out.println("addCard to monsterZone");
            super.addCard(card);
        }
    }
}
