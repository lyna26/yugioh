package com.example.yugioh.controllers;

import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.field.Field;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import lombok.Getter;


import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class FieldController implements Initializable {
    @FXML
    private GridPane handZone;
    @FXML
    private HBox monsterZone;
    @FXML
    private HBox spellTrapZone;
    @FXML
    private StackPane mainDeckZone;
    @FXML
    private StackPane extraDeckZone;

    Field field;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        field = new Field();
    }

    private void UpdateMainDeckZone(CardImpl card) {
        card.setImage(new Image(card.getBackImage()));
        mainDeckZone.getChildren().add(card);
    }
}