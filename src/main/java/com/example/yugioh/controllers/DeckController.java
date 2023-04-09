package com.example.yugioh.controllers;

import com.example.yugioh.factory.cardFactory.CardFactory;
import com.example.yugioh.engines.DataBaseEngine;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.deck.Deck;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeckController {
    @FXML
    GridPane cardList;

    private Deck deck;

    public void displayCard() throws SQLException {
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
        /* the drag-and-drop gesture entered the target */
        /* show to the user that it is an actual gesture target */
        if (event.getGestureSource() != cardList && event.getDragboard().hasString()) {
            cardList.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        }
        event.consume();
    }

    public void DragExited(DragEvent event) {
        System.out.println("drag exited");
        /* mouse moved away, remove the graphical cues */
        cardList.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(5))));
        event.consume();
    }

    public void DragDropped(DragEvent event) throws SQLException {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            if (deck.getCardList().size() < deck.getMaxCard()) {
                ResultSet res = DataBaseEngine.selectCardById(db.getString());
                while (res.next()) {
                    Card c = CardFactory.createCard(res);
                    addCard(c);
                    deck.addCard(c);
                }
                success = true;
            }
        }
        /* let the source know whether the string was successfully
         * transferred and used */
        event.setDropCompleted(success);
        event.consume();
    }

    public void addCard(Card card) throws SQLException {

        card.setImage(new Image(card.getCardImage()));
        ImageView v = card;
        v.setPreserveRatio(true);
        v.setFitWidth(100);
        v.setFitHeight(100);

        int row = cardList.getRowCount();
        int childrenSize = cardList.getChildren().size();
        int col = cardList.getChildren().size() % 5;
        System.out.println("children size" + childrenSize);
        System.out.println("row" + row);
        System.out.println("col" + col);

        if (childrenSize != 0 && col % 5 == 0) {
            row++;
        }
        cardList.add(v, col, row - 1);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void remove(Event event) {
        System.out.println("eventt");
        Card node = (Card) event.getTarget();
        System.out.println("nod" + node.getName());

        int colIndex = GridPane.getColumnIndex(node);
        int rowlIndex = GridPane.getRowIndex(node);
        System.out.println("cell clicked :" + colIndex + "" + rowlIndex);
        deck.removeCard(node);
        cardList.getChildren().remove(node);

    }
}
