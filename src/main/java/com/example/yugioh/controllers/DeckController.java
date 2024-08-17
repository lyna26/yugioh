package com.example.yugioh.controllers;

import com.example.yugioh.engines.DeckRepository;
import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.CardImpl;
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
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
        deck.getCardList().forEach(this::addCard);
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
                List<CardImpl> res = DeckRepository.selectCardById(db.getString());
                for (CardImpl card: res) {
                    addCard(card);
                    deck.addCard(card);
                }
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    public void addCard(CardImpl card){
        try {
            deck.addCard(card);
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


        card.setImage(new Image(card.getSmallCardImage()));

        card.setFitWidth(150);
        card.setFitHeight(150);
    }
    public void remove(Event event) {
        CardImpl node = (CardImpl) event.getTarget();
        deck.removeCard(node);
        cardList.getChildren().remove(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (deck != null) {
            this.deckType.setText(deck.getType());
            displayCard();
        }
    }
}
