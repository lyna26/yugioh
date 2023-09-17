package com.example.yugioh.controllers;

import com.example.yugioh.models.card.CardImpl;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;

public class CardController extends Region {

    private final CardImpl card;

    public CardController(CardImpl card) {
        this.card = card;
        this.card.setImage(new Image(this.card.getSmallCardImage()));
        getChildren().add(card);
    }

    @Override
    protected void layoutChildren() {
        // usable width and height:
        double width = getWidth() - snappedLeftInset() - snappedRightInset();
        double height = getHeight() - snappedTopInset() - snappedBottomInset();
        card.setFitWidth(Math.max(1, width));
        card.setFitHeight(Math.max(1, height));
        double imageWidth = card.getBoundsInLocal().getWidth();
        double imageHeight = card.getBoundsInLocal().getHeight();
        // center image (can also make this more complex and support alignment):
        double x = snappedLeftInset() + (width - imageWidth) / 2;
        double y = snappedTopInset() + (height - imageHeight) / 2;
        card.relocate(x , y);
    }
}
