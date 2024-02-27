package com.example.yugioh.controllers;


import com.example.yugioh.enums.CardType;
import com.example.yugioh.models.card.CardImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;

@Getter
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

    public void addInfos(CardImpl card) {
        String race = card.getRace();

        int atk = card.getAtk();

        String newText = cardName.getText() + "\n";

        if (card.getTypes().contains("Monster")){
            String attribute = card.getAttribute();

            newText =  newText +
                    attribute  + "/" +  race ;

            //case link monster
            if(card.getTypes().contains("Link")){
                newText += "[" + card.getLinkArraws() + "]" + "\n" + atk + "/LINK-"  + card.getLink();
            }
            else{
                int level = card.getLevel();

                newText += "★" + level;

                if (card.getTypes().contains("Pendulum")){
                    newText += "♦" + card.getScale();
                }

                newText += "\n" + atk +"/" + card.getDef();
            }
        }
        else {
            String cardType = "";

            for (CardType type : CardType.values()) {
                if(card.getTypes().contains(type.getType())){
                    cardType = type.getType();
                }
            }
            newText =  newText +
                    cardType + "|" + race;
        }
        cardName.setText(newText);
    }
}
