package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.engines.DeckRepository;
import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.deck.DeckBuilderModel;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@Slf4j
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
    AnchorPane deckSet;
    @FXML
    DeckSetController deckSetController;

    private DeckBuilderModel deckBuilderModel;

    private final ObservableList<CardImpl> cardResults = FXCollections.observableArrayList();

    @FXML
    private void searchCard(){
        String cardName = cardToSearch.getText();
        if (!cardName.isEmpty()) {
            performSearch(cardName);
        }
    }

    private void performSearch(String cardName) {
        try{
            log.info("searching for card" + cardName);
            List<CardImpl> matchingCards = deckBuilderModel.searchCardsByName(cardName);
            cardResults.setAll(matchingCards);
        }
        catch (Exception exception){
           log.error( "error when trying to search for this card " + cardName + exception.getMessage());
        }
    }

    @FXML
    private void saveGame(){
        log.info("saving game changes");
        Game.getInstance().getPlayer().setModifiedDeck(deckBuilderModel.getDeckSet());
        Game.save();
    }

    private Node createCardResultNode(CardImpl card){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/card/CardResult.fxml"));
            Parent cardResult = loader.load();

            CardResultController controller = loader.getController();
            controller.setCard(card);

            configureCardResultEvent(card, cardResult);

            return cardResult;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void configureCardResultEvent(CardImpl card, Parent cardResult) {
        cardResult.setOnMouseEntered((event -> cardInfosController.setCard(card)));
        cardResult.setOnDragDetected(event -> {
            handleDrag(card, event, cardResult);
        });
    }

    private static void handleDrag(CardImpl card, MouseEvent event, Parent cardResult) {
        Dragboard db = cardResult.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putString(String.valueOf(card.getCardId()));
        db.setContent(content);
        event.consume();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deckBuilderModel = new DeckBuilderModel(Game.getInstance().getPlayer().getModifiedDeck(), new DeckRepository());

        cardResults.addListener((ListChangeListener<CardImpl>) change -> {
            cardResultGrid.getChildren().clear();

            AtomicInteger countRow = new AtomicInteger(0);

            cardResults.forEach(card -> {
                Node cardResult = createCardResultNode(card);

                cardResultGrid.add(cardResult, 0, countRow.get());
                    countRow.getAndIncrement();
            });
        });
    }
}