package com.example.yugioh.controllers;

import com.example.yugioh.models.deck.DeckSet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeckSetController {

    @FXML
    Label deckSetName;

    private DeckSet deckSet;

    public void setDeckSetName() {
        deckSetName.setText(deckSet.getName());
    }

    public Label getDeckSetName() {
        return deckSetName;
    }

    public DeckSet getDeckSet() {
        return deckSet;
    }

    public void setDeckSet(DeckSet deckSet) {
        this.deckSet = deckSet;
    }
}
