package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.exceptions.CantRemoveCardFromDeckException;
import com.example.yugioh.exceptions.DeckTypeNullException;
import com.example.yugioh.models.card.Card;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */

@Slf4j
@Getter
public abstract class DeckImpl implements Serializable, Deck{
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Card> cardList;
    private final DeckType type;

    public DeckImpl(final DeckType type) {
        if (type == null) {
            String errorMessage = "type can't be null when creating a deck";
            log.error(errorMessage);
            throw new DeckTypeNullException(errorMessage);
        }
        this.cardList = new ArrayList<>();
        this.type = type;
    }

    private boolean isValidWithSize(int size) {
        return isMoreEqualThanMin(size) && isLessEqualThanMax(size);

    }

    private boolean isValidSize() {
        return isValidWithSize(cardList.size());
    }

    public boolean isValidDeck() {
        return isValidSize();
    }

    private boolean isLessEqualThanMax(int size) {
        return size <= getMaxCard();
    }

    private boolean isMoreEqualThanMin(int size) {
        return size >= getMinCard();
    }

    public void addCard(Card card) {
        if (isLessEqualThanMax(cardList.size() + 1)) {
            cardList.add(card);
        } else {
            log.warn("Deck is full, cannot add more cards.");
        }
    }

    public void removeCard(Card card) {
        boolean remove = cardList.remove(card);
        if (!remove) {
            log.error("error when removing card");
            throw new CantRemoveCardFromDeckException("error when removing card");
        }
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

    public String getType() {
        return this.type.name();
    }
}
