package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.views.DeckSetCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.yugioh.cmn.SceneUtility.HEIGHT;
import static com.example.yugioh.cmn.SceneUtility.WIDTH;

@Slf4j
public class DeckMenuController implements Initializable {
    @FXML
    private ListView<DeckSet> deckListView;

    private DeckMenuModel deckMenuModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deckMenuModel = new DeckMenuModel(Game.getInstance().getPlayer().getDecks());
        initListView();
    }

    private void initListView() {
        deckListView.setItems(deckMenuModel.getDeckSets());
        deckListView.setCellFactory(listView -> new DeckSetCell());

        deckListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                DeckSet selectedDeckSet = deckListView.getSelectionModel().getSelectedItem();
                Game.getInstance().getPlayer().setModifiedDeck(selectedDeckSet);
                openDeckBuilder(selectedDeckSet);
            }
        });
    }

    private void openDeckBuilder(DeckSet deckSet) {
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