package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */

@Slf4j
public class

Deck implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Getter
    private List<Card> cardList;
    private DeckType type;

    public Deck(DeckType type) {
        this.cardList = new ArrayList<>();
        this.type = type;
    }


    public boolean isValidSize() {
        int size = cardList.size();
        return (size >= getMinCard() || size <= getMaxCard());
    }

    public boolean isValid() {
        return isValidSize();
    }

    public void addCard(Card card) {
        if (cardList.size() + 1 <= getMaxCard()) {
            cardList.add(card);
        } else {
            log.warn("Deck is full, cannot add more cards.");
        }
    }

    public Optional<Card> drawCard() {
        return cardList.stream().findFirst();
    }

    public void removeCard(Card card) {
        cardList.removeIf(c -> c.equals(card));
    }

    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public int getMinCard() {
        return this.type.getMinCard();
    }

    public int getMaxCard() {
        return this.type.getMaxCard();
    }

    public String getType(){
        return this.type.name();
    }
}
