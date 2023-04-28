package com.example.yugioh.controllers;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.MonsterCard;
import com.example.yugioh.models.field.Field;
import javafx.collections.ListChangeListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
public class FieldController implements Initializable {
    @FXML
    GridPane handZone;

    @FXML
    HBox monsterZone;

    private Field field;



    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.field = new Field();
        field.getHandZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Card card : change.getAddedSubList()) {
                        int size = handZone.getChildren().size();
                        handZone.add(card, size, 0);
                        System.out.println("size=" + size);
                        System.out.println("added" + card.getName());
                    }
                }
                else if (change.wasRemoved()) {
                    for (Card card : change.getRemoved()) {
                        handZone.getChildren().remove(card);
                        System.out.println("removed"+ card.getName());
                    }
                }
            }
        });

        //Monster zone handling
        field.getMonsterZone().getCards().addListener((ListChangeListener<Card>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Card card : change.getAddedSubList()) {
                        int size = 0;
                        StackPane pane = (StackPane) monsterZone.getChildren().get(0);
                        pane.getChildren().add(card);
                        System.out.println("added to field" + card.getName());
                    }
                }
                else if (change.wasRemoved()) {
                    for (Card card : change.getRemoved()) {
                        int size = 0;
                        StackPane pane = (StackPane) monsterZone.getChildren().get(0);
                        pane.getChildren().remove(card);
                        System.out.println("removed from field"+ card.getName());
                    }
                }
            }
        });
    }
}