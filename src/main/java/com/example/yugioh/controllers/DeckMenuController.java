package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.views.DeckSetCell;
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
    private ListView<DeckSet> deckListView;

    private DeckMenuModel deckMenuModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createDeck.setOnMouseClicked(event -> createDeck());
        deckMenuModel = new DeckMenuModel(Game.getInstance().getPlayer().getDecks());
        initListView();
    }

    void createDeck(){
        String name = askForDeckName();

        // If a valid name is returned, create the deck
        if (name != null && !name.trim().isEmpty()) {
            deckMenuModel.createDeck(name);
        }
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
        deckListView.setItems(deckMenuModel.getDeckSets());
        deckListView.setCellFactory(listView -> new DeckSetCell());

        deckListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                DeckSet selectedDeckSet = deckListView.getSelectionModel().getSelectedItem();
                Game.getInstance().getPlayer().setModifiedDeck(selectedDeckSet);
                openDeckBuilder();
            }
        });
    }

    private void openDeckBuilder() {
        try {
            changeScene();
        } catch (IOException e) {
            log.error("Failed to open deck builder", e);
        }
    }

    private void changeScene() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Deck Builder");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/deck/DeckBuilder.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}