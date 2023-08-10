package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.engines.DataBaseEngine;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.deck.DeckSet;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class DeckBuilderController implements Initializable {
    @FXML
    TextArea cardToSearch;
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

    private final ObservableList<Card> cardResults = FXCollections.observableArrayList();


    public void setDecks(){
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

    public Node  displayCardResults(Card card){

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/CardResult.fxml"));

        try {
            Parent cardResult = loader.load();
            CardResultController controller = loader.getController();
            controller.setCard(card);


            cardResult.setOnMouseEntered((event -> cardInfosController.setCard(card)));

            cardResult.setOnDragDetected(event -> {
                Dragboard db = cardResult.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(card.getCardId()));
                db.setContent(content);
                event.consume();
            });

            return cardResult;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchCard() throws SQLException{
        String cardsToSearch = cardToSearch.getText();
        if (!cardsToSearch.isEmpty()) {
            cardResults.setAll(DataBaseEngine.selectCards(cardsToSearch));
        }
    }

    public void saveGame() throws IOException {
        int index = Game.getInstance().getPlayer().getDecks().indexOf(deck);
        Game.getInstance().getPlayer().getDecks().get(index).setSideDeck(sideDeckController.getDeck());
        Game.getInstance().getPlayer().getDecks().get(index).setExtraDeck(extraDeckController.getDeck());
        Game.getInstance().getPlayer().getDecks().get(index).setMainDeck(mainDeckController.getDeck());
        Game.save();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cardResults.addListener((ListChangeListener<Card>) change -> {
            cardResultGrid.getChildren().clear();
            AtomicInteger countRow = new AtomicInteger(1);
            AtomicInteger countCol = new AtomicInteger();

            cardResults.forEach(card -> {
                Node cardResult = displayCardResults(card);
                cardResultGrid.add(cardResult, countCol.get(), countRow.get());
                countCol.getAndIncrement();

                if (countCol.get() == 2) {
                    countCol.set(0);
                    countRow.getAndIncrement();
                }
            });
        });
    }
}