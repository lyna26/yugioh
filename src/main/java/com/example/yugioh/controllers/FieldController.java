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
    public GridPane handZone;

    @FXML
    public HBox monsterZone;

    @FXML
    public HBox spellTrapZone;

    Field field = new Field();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeHandZoneListener();
        initializeMonsterZoneListener();
        initializeSpellTrapZoneListener();
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
        System.out.println("Removed " + card.getName() + " from spellTrapZone");
    }

    private void addToSpellTrapZone(Card card) {
        if (card.getFace() == Face.DOWN) {
            card.setImage(new Image(card.getBackImage()));
        }
        int indexCard = field.getSpellTrapZone().getCards().indexOf(card);
        StackPane pane = (StackPane) monsterZone.getChildren().get(indexCard);
        pane.getChildren().add(card);
        System.out.println("Added " + card.getName() + " to spellTrapZone");
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
        System.out.println("Added " + card.getName() + " to handZone");
    }

    private void removeFromHandZone(Card card) {
        handZone.getChildren().remove(card);
        System.out.println("Removed " + card.getName() + " from handZone");
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
        System.out.println("Added " + card.getName() + " to monsterZone");
    }

    private void removeFromMonsterZone(Card card) {
        StackPane pane = (StackPane) monsterZone.getChildren().get(0);
        pane.getChildren().remove(card);
        System.out.println("Removed " + card.getName() + " from monsterZone");
    }
}