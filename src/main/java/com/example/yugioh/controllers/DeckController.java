package com.example.yugioh.controllers;

import com.example.yugioh.engines.DataBaseEngine;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.deck.Deck;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DeckController implements Initializable {
    @FXML
    GridPane cardList;

    @FXML
    Label deckType;
    private Deck deck;

    public void displayCard(){
        for (Card c : deck.getCardList()) {
            addCard(c);
        }
    }

    public void dragOver(DragEvent event) {
        if (event.getGestureSource() != cardList && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
        event.consume();
    }

    public void dragEntered(DragEvent event) {
        if (event.getGestureSource() != cardList && event.getDragboard().hasString()) {
            cardList.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        }
        event.consume();
    }

    public void dragExited(DragEvent event) {
        cardList.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(5))));
        event.consume();
    }

    public void dragDropped(DragEvent event) throws SQLException {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            if (deck.getCardList().size() < deck.getMaxCard()) {
                List<Card> res = DataBaseEngine.selectCardById(db.getString());
                for (Card card: res) {
                    addCard(card);
                    deck.addCard(card);
                }
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    public void addCard(Card card){
        card.setImage(new Image(card.getSmallCardImage()));

        int row = cardList.getRowCount();
        int childrenSize = cardList.getChildren().size();
        int col = cardList.getChildren().size() % 5;

        if (childrenSize != 0 && col % 5 == 0) {
            row++;
        }
        cardList.add(card, col, row - 1);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void remove(Event event) {
        Card node = (Card) event.getTarget();
        deck.removeCard(node);
        cardList.getChildren().remove(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
