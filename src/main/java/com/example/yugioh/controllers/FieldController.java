package com.example.yugioh.controllers;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.field.Field;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldController{
    @FXML
    private StackPane fieldZone;
    @FXML
    private GridPane monsterZone;
    @FXML
    private StackPane graveyardZone;
    @FXML
    private StackPane banishZone;
    @FXML
    private StackPane extraDeckZone;
    @FXML
    private StackPane mainDeckZone;

    @FXML
    private GridPane handZone;

    private Field field = new Field();

    private ListView<Card> handCards;
    @FXML
    private GridPane spellMonsterZone;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;

        handCards = new ListView<>(this.field.getHandZone().getCards());

        updateHandZone();

        // Ajouter un ChangeListener à la liste observable handCards
        handCards.getItems().addListener((ListChangeListener<Card>) change -> {
            // Effacer tous les enfants de handZone
            handZone.getChildren().clear();

            // Ajouter chaque élément de handCards à handZone
            updateHandZone();

        });
    }

    public void updateHandZone(){
        for (int i = 0; i < handCards.getItems().size(); i++) {
            Card card = handCards.getItems().get(i);
            // Créer un ImageView pour afficher l'image de la carte
            card.setImage(new Image(card.getCardImage()));
            card.setFitWidth(100);
            card.setPreserveRatio(true);
            // Ajouter le ImageView à handZone
            System.out.println("card to add" + card.getName());
            handZone.add(card, i, 0);
        }

    }
}
