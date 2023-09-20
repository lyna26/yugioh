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

    public void setCard(CardImpl card) {
        cardImage.setImage(new Image(card.getSmallCardImage()));
        cardName.setText(card.getName());
        addInfos(card);
    }

    public Label getCardName() {
        return cardName;
    }

    public ImageView getCardImage() {
        return cardImage;
    }

    public void addInfos(CardImpl card) {
        String race = card.getRace();

        String newText = cardName.getText() + "\n";

        if (card.getTypes().contains("Monster")){
            String attribute = card.getAttribute();
            int level = card.getLevel();
            int atk = card.getAtk();
            int def = card.getDef();

            newText =  newText +
                    race + "/" + attribute + "★" + level + "\n" +
                    atk + "/" + def;

        }
        else {
            String cardType = card.getClass().getName();

            newText =  newText +
                    cardType + "|" + race;
        }

        cardName.setText(newText);
    }
}
