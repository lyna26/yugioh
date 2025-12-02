package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.dto.card.CardDTO;
import javafx.scene.layout.Region;

public class CardController extends Region {

    private final CardDTO card;

    public CardController(CardDTO card) {
        this.card = card;
        //getChildren().add(new ImageView(new Image(this.card.getSmallCardImage())));
    }
}
