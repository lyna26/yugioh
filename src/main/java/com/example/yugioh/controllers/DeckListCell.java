package com.example.yugioh.controllers;

import com.example.yugioh.models.deck.DeckSet;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class DeckListCell extends ListCell<DeckSet> {
    @Override
    protected void updateItem(DeckSet deckSet, boolean empty) {
        super.updateItem(deckSet, empty);

        if (empty || deckSet == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Create a custom HBox or other layout to display deck information
            HBox content = new HBox();
            // Add labels, images, or other controls to display deck name, size, etc.
            setText(deckSet.getName()); // For basic display
            setGraphic(content);
        }
    }
}
