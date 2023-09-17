package com.example.yugioh.controllers;


import com.example.yugioh.models.card.CardImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class CardResultController{

    @FXML
    Label cardName;
    @FXML
    ImageView cardImage;

    public void setCard(CardImpl card) {
        cardImage.setImage(new Image(card.getSmallCardImage()));
        cardName.setText(card.getName());
    }

    public Label getCardName() {
        return cardName;
    }

    public ImageView getCardImage() {
        return cardImage;
    }
}
