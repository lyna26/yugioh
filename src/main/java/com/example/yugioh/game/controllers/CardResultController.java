package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.dto.card.CardImplDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardResultController{
    @FXML
    private Label cardName;
    @FXML
    private ImageView cardImage;

    private CardImplDTO card ;

    public void setCard(CardDTO card) {
        //cardImage.setImage(new Image(card.getSmallCardImage()));
        cardName.setText(card.getName());
    }
}
