package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.dto.card.CardDTO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInfosController implements Initializable {
    @FXML
    Text cardName;
    @FXML
    ImageView cardImage;
    @FXML
    Text cardDesc;

    private final ObjectProperty<CardDTO> cardProperty = new SimpleObjectProperty<>();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        cardProperty.addListener((observable, oldCard, newCard) -> {
            if (newCard != null) {
                cardName.setText(newCard.getName());
                cardDesc.setText(newCard.getDescription());
                //cardImage.setImage(new Image(newCard.getBigCardImage()));
            }
        });
    }

    public void setCard(CardDTO card) {
        cardProperty.set(card);
    }
}
