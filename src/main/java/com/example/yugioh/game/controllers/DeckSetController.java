package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import com.example.yugioh.auth.entity.deck.Deck;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Slf4j
public class DeckSetController implements Initializable {

    @FXML
    private AnchorPane mainDeck;
    @FXML
    private DeckController mainDeckController;

    @FXML
    private AnchorPane sideDeck;
    @FXML
    private DeckController sideDeckController;
    @FXML
    private AnchorPane extraDeck;
    @FXML
    private DeckController extraDeckController;

    private final ObjectProperty<DeckCollection> deckSet = new SimpleObjectProperty<>();

    public DeckSetController() {
        //this(Game.getInstance().getPlayer().getModifiedDeck());
    }

    public DeckSetController(DeckCollection deckCollection) {
        this.deckSet.set(deckCollection);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deckSet.addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
               // loadDeckFXML(mainDeck, newValue.getMainDeck(), mainDeckController);
                //loadDeckFXML(sideDeck, newValue.getSideDeck(), sideDeckController);
               // loadDeckFXML(extraDeck, newValue.getExtraDeck(), extraDeckController);
            }
        });
    }

    private void loadDeckFXML(AnchorPane pane, Deck deck, DeckController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/deck/Deck.fxml"));
            pane.getChildren().setAll((AnchorPane) loader.load());
            controller = loader.getController();
            controller.setDeck(deck);
        } catch (IOException e) {
            log.error("Failed to load Deck.fxml", e);
        }
    }
}
