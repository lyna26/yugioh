package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a deck of cards in the Yu-Gi-Oh trading card game.
 * A deck is defined by a list of cards and a minimum/maximum number of cards allowed.
 */
public class Deck implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int minCard;
    private final int maxCard;
    private final List<Card> cardList;

    /**
     * Constructs a new deck of the specified type.
     *
     * @param deckType The type of the deck, which determines its minimum and maximum size.
     */
    public Deck(DeckType deckType) {
        this.cardList = new ArrayList<>();
        this.minCard = deckType.getMinCard();
        this.maxCard = deckType.getMaxCard();
    }

    /**
     * Checks if the number of cards in the deck is valid according to its minimum and maximum size.
     *
     * @return True if the deck's size is within the allowed range, false otherwise.
     */
    public boolean isValidSize() {
        int size = cardList.size();
        return (size >= minCard || size <= maxCard);
    }

    /*public boolean isValidCardList()
    {
        TODO correct this function when the game handles card limits
        Map<String,Long> occurrenceMap = cardList.stream().collect(Collectors.groupingBy(card -> card.getName(),
                Collectors.counting()));
        return (cardList.stream().filter(card -> card.getLIMIT().getNbCopies()< occurrenceMap.get(card.getName()))
                .count() == 0);
    }*/

    /**
     * Checks if the deck is valid, i.e., if it meets all the criteria for a valid deck in the game.
     *
     * @return True if the deck is valid, false otherwise.
     */
    public boolean isValid() {
        return isValidSize();
    }

    /**
     * Adds a card to the deck.
     *
     * @param card The card to add.
     */
    public void addCard(Card card) {
        cardList.add(card);
    }

    /**
     * Removes a card from the deck.
     *
     * @param c The card to remove.
     */
    public void removeCard(Card c) {
        cardList.remove(c);
    }

    /**
     * Removes and returns the first card in the deck.
     *
     * @return The first card in the deck, or null if the deck is empty.
     */
    public Card removeFirstCard() {
        return cardList.remove(0);
    }

    /**
     * Shuffles the cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(cardList);
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int getMinCard() {
        return minCard;
    }

    public int getMaxCard() {
        return maxCard;
    }
}
