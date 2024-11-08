package com.example.yugioh.models.deck;

import com.example.yugioh.engines.DeckRepository;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import lombok.Getter;

import java.util.List;


@Getter
public class DeckBuilderModel {
    private final DeckRepository deckRepository;
    private DeckSet deckSet;

    public DeckBuilderModel(DeckSet deckSet, DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
        this.deckSet = deckSet;
    }

    public List<Card> searchCardsByName(String cardName){
        return deckRepository.selectCardsByName(cardName);
    }
}
