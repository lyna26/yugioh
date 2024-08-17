package com.example.yugioh.controllers;

import com.example.yugioh.models.deck.DeckSet;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class DeckMenuModel {
    private final ObjectProperty<DeckSet> selectedDeck = new SimpleObjectProperty<>();
    private final ObservableList<DeckSet> deckSets = FXCollections.observableArrayList();

    public DeckMenuModel(List<DeckSet> initialDeckSets) {
        deckSets.addAll(initialDeckSets);
    }

    public ObservableList<DeckSet> getDeckSets() {
        return deckSets;
    }

    public ObjectProperty<DeckSet> getSelectedDeck() {
        return selectedDeck;
    }

    public void createDeck(String deckName) {
        DeckSet newDeck = new DeckSet(deckName);
        deckSets.add(newDeck);
    }
}
