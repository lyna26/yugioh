package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.engines.DataBaseEngine;
import com.example.yugioh.models.card.CardInfo;
import com.example.yugioh.models.deck.DeckSet;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeckBuilderController implements Initializable {
    @FXML
    TextArea CardToSearch;
    @FXML
    GridPane cardResultGrid;
    @FXML
    AnchorPane cardInfos;
    @FXML
    CardInfosController cardInfosController;

    @FXML
    AnchorPane mainDeck;
    @FXML
    DeckController mainDeckController;
    @FXML
    AnchorPane sideDeck;
    @FXML
    DeckController sideDeckController;
    @FXML
    AnchorPane extraDeck;
    @FXML
    DeckController extraDeckController;

    private DeckSet deck;

    public void setDecks() throws SQLException {
        mainDeckController.setDeck(deck.getMainDeck());
        extraDeckController.setDeck(deck.getExtraDeck());
        sideDeckController.setDeck(deck.getSideDeck());

        mainDeckController.displayCard();
        extraDeckController.displayCard();
        sideDeckController.displayCard();
    }

    public void setDeck(DeckSet deck) {
        this.deck = deck;
    }

    public void displayCardResults(ResultSet res) throws SQLException, IOException {
        int count = 0;
        int countRow = 1;
        int countCol = 0;

        while (res.next() && count < 20) {
            count++;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/CardResult.fxml"));
            Parent cardResult = loader.load();
            CardResultController controller = loader.getController();
            CardInfo cardInfos = new CardInfo(res);
            controller.setCardInfo(cardInfos);
            controller.displayInfos();
            cardResult.setUserData(controller);

            if (countCol == 2) {
                countCol = 0;
                ++countRow;
            }

            cardResult.setOnMouseEntered((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    Node node = event.getPickResult().getIntersectedNode();
                    while (node.getUserData() == null) {
                        node = node.getParent();
                    }
                    AnchorPane parent = (AnchorPane) node;
                    CardResultController cardC = (CardResultController) node.getUserData();
                    cardInfosController.setCardName(cardC.getCardInfo().getName());
                    cardInfosController.setCardImage(cardC.getCardInfo().getImageUrl());
                    cardInfosController.setCardDesc(cardC.getCardInfo().getDesc());
                }

            }));

            cardResult.setOnDragDetected(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    Node node = event.getPickResult().getIntersectedNode();

                    while (node.getUserData() == null) {
                        node = node.getParent();
                    }
                    AnchorPane parent = (AnchorPane) node;
                    CardResultController cardC = (CardResultController) node.getUserData();

                    /* drag was detected, start a drag-and-drop gesture*/
                    /* allow any transfer mode */
                    Dragboard db = cardResult.startDragAndDrop(TransferMode.ANY);

                    /* Put a string on a dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putString(String.valueOf(cardC.getCardInfo().getId()));
                    db.setContent(content);
                    event.consume();
                }
            });
            cardResultGrid.add(cardResult, countCol, countRow);
            GridPane.setMargin(cardResult, new Insets(10));

            countCol++;

        }
    }

    public void searchCard() throws SQLException, IOException {
        String cardToFind = CardToSearch.getText();
        if (!cardToFind.isEmpty()) {
            ResultSet res = DataBaseEngine.selectCards(cardToFind);
            displayCardResults(res);
        }
    }

    public void saveGame() throws IOException {
        int index = Game.getInstance().getPlayer().getDecks().indexOf(deck);
        System.out.println("index of the deck is" + index);
        Game.getInstance().getPlayer().getDecks().get(index).setSideDeck(sideDeckController.getDeck());
        Game.getInstance().getPlayer().getDecks().get(index).setExtraDeck(extraDeckController.getDeck());
        Game.getInstance().getPlayer().getDecks().get(index).setMainDeck(mainDeckController.getDeck());
        Game.save();
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("int");
    }
}