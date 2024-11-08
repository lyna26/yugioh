package com.example.yugioh.controllers;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class CardController extends Region {

    private final Card card;

    public CardController(Card card) {
        this.card = card;
        getChildren().add(new ImageView(new Image(this.card.getSmallCardImage())));
    }
}
