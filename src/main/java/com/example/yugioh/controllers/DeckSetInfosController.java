package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.models.deck.DeckSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class DeckSetInfosController extends HBox {
    @FXML
    private Label deckSetName;

    @FXML
    private Button deleteButton;

    private DeckSet deckSet;

    public void setData(DeckSet deckSet) {
        this.deckSet = deckSet;
        deckSetName.setText(deckSet.getName());
        deleteButton.setOnAction(event -> deleteDeckSet());
    }
    private void deleteDeckSet() {
        Game.getInstance().getPlayer().getDecks().remove(deckSet);
    }
}