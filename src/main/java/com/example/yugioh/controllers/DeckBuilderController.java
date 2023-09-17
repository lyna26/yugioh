package com.example.yugioh.controllers;

import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.deck.DeckBuilderModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
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

   DeckBuilderModel deckBuilder;

    private final ObservableList<CardImpl> cardResults = FXCollections.observableArrayList();

    @FXML
    private void searchCard(ActionEvent event) throws SQLException {
        String cardName = cardToSearch.getText();

        if (!cardName.isEmpty()) {
            List<CardImpl> matchingCards = deckBuilder.searchCardsByName(cardName);
            cardResults.setAll(matchingCards);
        }
    }

    public Node displayCardResults(CardImpl card){

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
        cardResults.addListener((ListChangeListener<CardImpl>) change -> {
            cardResultGrid.getChildren().clear();

            AtomicInteger countRow = new AtomicInteger(0);

            cardResults.forEach(card -> {
                Node cardResult = displayCardResults(card);

                cardResultGrid.add(cardResult, 0, countRow.get());
                    countRow.getAndIncrement();
            });
        });
    }
}