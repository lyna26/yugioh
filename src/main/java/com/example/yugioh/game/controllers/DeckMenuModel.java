package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.List;

@Getter
public class DeckMenuModel {
    private final ObservableList<DeckCollection> deckCollections;

    public DeckMenuModel(List<DeckCollection> initialDeckCollections) {
        this.deckCollections = FXCollections.observableArrayList(initialDeckCollections);
    }

    public DeckCollection createDeck(String deckName) {
        /*DeckSet newDeck = new DeckSet(deckName);
        deckSets.add(newDeck);
        return newDeck;*/return null;
    }
}
