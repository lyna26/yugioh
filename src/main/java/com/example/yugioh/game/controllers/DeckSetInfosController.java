package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeckSetInfosController extends HBox {
    @FXML
    private Label deckSetName;

    @FXML
    private Button deleteButton;

    private DeckCollection deckCollection;

    public void setData(DeckCollection deckCollection) {
        this.deckCollection = deckCollection;
        deckSetName.setText(deckCollection.getName());
        deleteButton.setOnAction(event -> deleteDeckSet());
    }
    private void deleteDeckSet() {
      //  Game.getInstance().getPlayer().getDecks().remove(deckSet);
    }
}