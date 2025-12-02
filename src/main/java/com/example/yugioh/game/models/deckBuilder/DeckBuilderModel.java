package com.example.yugioh.game.models.deckBuilder;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import lombok.Getter;


@Getter
public class DeckBuilderModel {
    //private final DeckRepositoryImpl deckRepository;
    private final DeckCollection deckCollection;

    public DeckBuilderModel(DeckCollection deckCollection) {
        this.deckCollection = deckCollection;
    }

    /*public List<CardDTO> searchCardsByName(String cardName){
        return deckRepository.selectCardsByName(cardName);
    }*/
}
