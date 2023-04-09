package com.example.yugioh.controllers;

import com.example.yugioh.models.card.CardInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CardResultController {

    @FXML
    Label cardName;
    @FXML
    ImageView cardImage;
    @FXML
    TextArea cardInfosFlow;
    private CardInfo cardInfos;

    public void setCardImage() {
        System.out.println(cardInfos.getImageUrl());
        cardImage.setImage(new Image(cardInfos.getImageUrl()));
        cardImage.setPreserveRatio(true);
        cardImage.setFitWidth(150);
        cardImage.setFitHeight(150);
    }

    public void setCardInfosFlow() {
        Text textName = new Text(cardInfos.getName());
        Text textRace = new Text(cardInfos.getRace());
        textName.setFill(Color.GREEN);
        Text textAtkDef = new Text(cardInfos.getAtk() + "/" + cardInfos.getDef());
        System.out.println("text" + textName.getText() + textRace.getText() + textAtkDef.getText());
        cardInfosFlow.setText(textName.getText() + textRace.getText() + textAtkDef.getText());
    }

    public CardInfo getCardInfo() {
        return cardInfos;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfos = cardInfo;
    }

    public void displayInfos() {
        setCardImage();
        setCardInfosFlow();
    }
}
