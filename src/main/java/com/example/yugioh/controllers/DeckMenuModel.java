package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.models.deck.DeckSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.List;

public class DeckMenuModel {
    private final ObjectProperty<DeckSet> selectedDeck = new SimpleObjectProperty<>();
    @Getter
    private final ObservableList<DeckSet> deckSets = FXCollections.observableArrayList();

    public DeckMenuModel(List<DeckSet> initialDeckSets) {
        deckSets.addAll(initialDeckSets);
    }

    public void createDeck(String deckName) {
        DeckSet newDeck = new DeckSet(deckName);
        Game.getInstance().getPlayer().getDecks().add(newDeck);
        deckSets.add(newDeck);
        Game.save();
    }
}
