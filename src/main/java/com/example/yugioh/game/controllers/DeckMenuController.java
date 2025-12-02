package com.example.yugioh.game.controllers;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import com.example.yugioh.game.application.Game;
import com.example.yugioh.game.models.deckBuilder.DeckBuilderModel;
import com.example.yugioh.game.views.DeckSetCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
public class DeckMenuController implements Initializable {
    @FXML
    private Button createDeck;
    @FXML
    private ListView<DeckCollection> deckListView;

    private DeckMenuModel deckMenuModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createDeck.setOnMouseClicked(event -> createDeck());
       // deckMenuModel = new DeckMenuModel(Game.getInstance().getPlayer().getDecks());
        initListView();
    }

    private void createDeck(){
        String name = askForDeckName();

        // If a valid name is returned, create the deck
        if (isValidDeckName(name)) {
            DeckCollection deck = deckMenuModel.createDeck(name);
           // Game.getInstance().getPlayer().getDecks().add(deck);
            Game.save();
        }
    }
    private static boolean isValidDeckName(String name) {
        return name != null && !name.trim().isEmpty();
    }
    private String askForDeckName() {
        // Create a new dialog window
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create New Deck");
        dialog.setHeaderText("Enter the name of the new deck");
        dialog.setContentText("Deck Name:");

        // Show the dialog and wait for user input
        Optional<String> result = dialog.showAndWait();

        // Return the deck name if the user has entered a name and clicked OK
        return result.orElse(null);
    }

    private void initListView() {
        deckListView.setItems(deckMenuModel.getDeckCollections());
        deckListView.setCellFactory(listView -> new DeckSetCell());
        deckListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                DeckCollection selectedDeckCollection = deckListView.getSelectionModel().getSelectedItem();
               // DeckBuilderModel dbm = new DeckBuilderModel(selectedDeckCollection, new DeckRepositoryImpl());
                openDeckBuilder(null);
            }
        });
    }

    private void openDeckBuilder(DeckBuilderModel dbm) {
        try {
            changeScene(dbm);
        } catch (IOException e) {
            log.error("Failed to open deck builder", e);
        }
    }

    private void changeScene(DeckBuilderModel dbm) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Deck Builder");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/deck/DeckBuilder.fxml"));
        fxmlLoader.setControllerFactory( param -> {
                    DeckBuilderController controller = new DeckBuilderController();
                    //controller.setDeckSet(dbm); // Initialisez avec le DeckSet sélectionné
                    return controller;
                });
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}