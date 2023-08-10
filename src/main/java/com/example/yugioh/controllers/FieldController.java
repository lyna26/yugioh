package com.example.yugioh.controllers;

import com.example.yugioh.enums.Face;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.field.Field;
import javafx.collections.ListChangeListener;

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

    Field field = new Field();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeHandZoneListener();
        initializeMonsterZoneListener();
        initializeSpellTrapZoneListener();
        initializeMainDeckListener();
        initializeExtraDeckListener();
    }

    private void initializeSpellTrapZoneListener() {
        field.getSpellTrapZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(this::addToSpellTrapZone);
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(this::removeFromSpellTrapZone);
                }
            }
        });
    }

    private void removeFromSpellTrapZone(Card card) {
        StackPane pane = (StackPane) spellTrapZone.getChildren().get(0);
        pane.getChildren().remove(card);
    }

    private void addToSpellTrapZone(Card card) {
        if (card.getFace() == Face.DOWN) {
            card.setImage(new Image(card.getBackImage()));
        }
        int indexCard = field.getSpellTrapZone().getCards().indexOf(card);
        StackPane pane = (StackPane) monsterZone.getChildren().get(indexCard);
        pane.getChildren().add(card);
    }

    private void initializeHandZoneListener() {
        field.getHandZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(this::addToHandZone);
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(this::removeFromHandZone);
                }
            }
        });
    }

    private void addToHandZone(Card card) {
        int size = handZone.getChildren().size();
        StackPane root = new StackPane();
        root.getChildren().add(card);
        handZone.add(root, size, 0);
    }

    private void removeFromHandZone(Card card) {
        handZone.getChildren().remove(card);
    }

    private void initializeMonsterZoneListener() {
        field.getMonsterZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(this::addToMonsterZone);
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(this::removeFromMonsterZone);
                }
            }
        });
    }

    private void addToMonsterZone(Card card) {
        if (card.getFace() == Face.DOWN) {
            card.setImage(new Image(card.getBackImage()));
        }
        int indexCard = field.getMonsterZone().getCards().indexOf(card);
        StackPane pane = (StackPane) monsterZone.getChildren().get(indexCard);
        pane.getChildren().add(card);
    }

    private void removeFromMonsterZone(Card card) {
        StackPane pane = (StackPane) monsterZone.getChildren().get(0);
        pane.getChildren().remove(card);
    }
    private void initializeMainDeckListener() {
        field.getMainDeckZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(this::addToMainDeck);
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(this::removeFromMainDeck);
                }
            }
        });
    }

    private void addToMainDeck(Card card) {
        card.setImage(new Image(card.getBackImage()));
        mainDeckZone.getChildren().add(card);
    }

    private void removeFromMainDeck(Card card) {
        mainDeckZone.getChildren().remove(card);
    }

    private void initializeExtraDeckListener() {
        field.getExtraDeckZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    change.getAddedSubList().forEach(this::addToExtraDeck);
                } else if (change.wasRemoved()) {
                    change.getRemoved().forEach(this::removeFromExtraDeck);
                }
            }
        });
    }

    private void addToExtraDeck(Card card) {
        card.setImage(new Image(card.getBackImage()));
        extraDeckZone.getChildren().add(card);
    }

    private void removeFromExtraDeck(Card card) {
        extraDeckZone.getChildren().remove(card);
    }
}