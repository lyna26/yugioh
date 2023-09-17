package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.deck.Deck;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Optional;

@Setter
@Getter
@ToString
public class MainDeckZone extends ZoneImpl{

    private Deck mainDeck;

    public MainDeckZone() {
        super();
    }

    public void addCard(CardImpl card) {
        mainDeck.addCard( card);
    }

    /**
     * @param card
     */
    @Override
    public void removeCard(CardImpl card) {
        mainDeck.removeCard(card);
    }


    public void removeCard(Card card) {
        mainDeck.removeCard(card);
    }

    public void shuffle() {
        mainDeck.shuffle();
    }

    public Optional<CardImpl> draw(){
        return mainDeck.drawCard();
    }
}