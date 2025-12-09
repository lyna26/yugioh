package com.example.yugioh.auth.dto.deck;


import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.entity.deck.Deck;
import com.example.yugioh.auth.enums.DeckType;
import com.example.yugioh.auth.factory.card.CardFactoryImpl;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */

@Getter
@Slf4j
public abstract class DeckDTO {

    int id;
    List<CardDTO> cardList = new ArrayList<>();
    DeckType type;

    public DeckDTO(final Deck deck) {
        id = deck.getId();
        type = DeckType.valueOf(deck.getType());
        cardList = deck.getCardList().stream()
                            .map(CardFactoryImpl::createCard).collect(Collectors.toCollection(ArrayList::new));

    }


    private boolean isValidSize() {
        int size = cardList.size();
        return size >= getMinCard() && size <= getMaxCard();
    }


    public boolean isValidDeck() {
        return isValidSize();
    }

    protected abstract boolean isValidType(CardDTO dto);


    public void addCard(CardDTO card) {
        int newSize = cardList.size() + 1;
        if (isValidType(card) && newSize <= getMaxCard()) {
            cardList.add(card);
        } else {
            log.warn("Deck is full, cannot add more cards.");
        }
    }

    public void removeCard(CardDTO card) {
        cardList.remove(card);
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
