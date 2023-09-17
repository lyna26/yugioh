package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.Card;

import com.example.yugioh.models.card.CardImpl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A class representing a deck of cards in the Yu-Gi-Oh trading card game.
 */

@Getter
@Setter
public class Deck implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int minCard;
    private final int maxCard;
    private List<CardImpl> cardList;

    public Deck(DeckType deckType) {
        this.cardList = new ArrayList<>();
        this.minCard = deckType.getMinCard();
        this.maxCard = deckType.getMaxCard();
    }

    public boolean isValidSize() {
        int size = cardList.size();
        return (size >= minCard || size <= maxCard);
    }

    public boolean isValid() {
        return isValidSize();
    }
    public void addCard(CardImpl card) {
        cardList.add(card);
    }
    public Optional<CardImpl> drawCard() {
        return cardList.stream().findFirst();
    }
    public void removeCard(Card card) {
        cardList.remove(card);
    }
    public void shuffle() {
        Collections.shuffle(cardList);
    }
}
