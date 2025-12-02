package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.dto.card.CardImplDTO;
import com.example.yugioh.auth.entity.deck.Deck;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Getter
@Setter
public class DeckController implements Initializable {
    @FXML
    private GridPane cardList;

    @FXML
    private Label deckType;

    private Deck deck;


    public DeckController() {}

    public void displayCard(){
        return;
        //deck.getCardList().forEach(this::addCard);
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
        /*Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            if (deck.getCardList().size() < deck.getMaxCard()) {
                List<Card> res = DeckRepositoryImpl.selectCardById(db.getString());
                for (Card card: res) {
                    addCard(card);
                    deck.addCard(card);
                }
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();*/
    }

    public void addCard(CardDTO card){
        try {
            //deck.addCard(card);
            CardController cardController = new CardController(card);

            int row = cardList.getRowCount();
            int childrenSize = cardList.getChildren().size();
            int col = childrenSize % 5;

            if (childrenSize != 0 && col % 5 == 0) {
                row++;
            }
            cardList.add(cardController, col, row - 1);
        } catch (Exception ignored) {

        }


       /* card.setImage(new Image(card.getSmallCardImage()));

        card.setFitWidth(150);
        card.setFitHeight(150);*/
    }
    public void remove(Event event) {
        CardImplDTO node = (CardImplDTO) event.getTarget();
        //deck.removeCard(node);
        cardList.getChildren().remove(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (deck != null) {
            //this.deckType.setText(deck.getType());
            displayCard();
        }
    }
}
