package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class DeckListCell extends ListCell<DeckCollection> {
    @Override
    protected void updateItem(DeckCollection deckCollection, boolean empty) {
        super.updateItem(deckCollection, empty);

        if (empty || deckCollection == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Create a custom HBox or other layout to display deck information
            HBox content = new HBox();
            // Add labels, images, or other controls to display deck name, size, etc.
            setText(deckCollection.getName()); // For basic display
            setGraphic(content);
        }
    }
}
