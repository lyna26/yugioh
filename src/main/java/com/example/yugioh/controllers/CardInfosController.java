package com.example.yugioh.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardInfosController {
    @FXML
    Label cardName;
    @FXML
    ImageView cardImage;
    @FXML
    TextArea cardDesc;

    public Label getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName.setText(cardName);
    }

    public ImageView getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage.setImage(new Image(cardImage));
    }

    public TextArea getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc.setText(cardDesc);
    }
}
