package com.example.yugioh.controllers;

import com.example.yugioh.models.card.CardImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardResultController{
    @FXML
    private Label cardName;
    @FXML
    private ImageView cardImage;

    private CardImpl card ;

    public void setCard(CardImpl card) {
        cardImage.setImage(new Image(card.getSmallCardImage()));
        cardName.setText(card.getName());
    }
}
