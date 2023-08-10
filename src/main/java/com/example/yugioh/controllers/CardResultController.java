package com.example.yugioh.controllers;

import com.example.yugioh.models.card.ICard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CardResultController implements Initializable {

    @FXML
    Label cardName;
    @FXML
    ImageView cardImage;
    @FXML
    TextArea cardInfosFlow;

    private ObjectProperty<ICard> card = new SimpleObjectProperty<>();

    public void setCardImage() {
        cardImage.setImage(new Image(card.get().getBigCardImage()));
    }

    public void setCard(ICard card) {
        this.card.set(card);
    }

   public void setCardInfosFlow() {
        ICard cardToDisplay = card.get();
        cardInfosFlow.appendText(cardToDisplay.getName());

        if (card.get().getTypes().contains("Monster")){
            cardInfosFlow.appendText(String.join("/",cardToDisplay.getAttribute(),cardToDisplay.getRace()));
            cardInfosFlow.appendText(String.join("/",String.valueOf(cardToDisplay.getAtk()), String.valueOf(cardToDisplay.getDef())));
        }
        else {
            cardInfosFlow.appendText(String.join("|", cardToDisplay.getTypes().get(0), cardToDisplay.getRace()));
        }
    }
    public void displayInfos() {
        setCardImage();
        setCardInfosFlow();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        card.addListener((obs, oldCard, newCard) -> displayInfos());
    }
}
