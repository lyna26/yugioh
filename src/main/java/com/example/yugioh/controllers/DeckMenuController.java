package com.example.yugioh.controllers;

import com.example.yugioh.application.Game;
import com.example.yugioh.models.deck.DeckSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeckMenuController implements Initializable {

    @FXML
    public Button searchDeck;
    @FXML
    ListView deckList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deckList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                AnchorPane selectedItem = (AnchorPane) deckList.getSelectionModel().getSelectedItem();
                DeckSetController controller = (DeckSetController) selectedItem.getUserData();
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Overlay windows");
                try {
                    String interfacePath = "/com/example/yugioh/fxml/" + "DeckBuilder.fxml";
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(interfacePath));
                    Parent root = fxmlLoader.load();
                    DeckBuilderController con = fxmlLoader.getController();
                    con.setDeck(controller.getDeckSet());
                    con.setDecks();
                    Scene scene = new Scene(root, 800, 800);
                    stage.setScene(scene);
                    stage.show();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        displayListDeck();
    }

    private void displayListDeck() {
        deckList.getItems().clear();
        for (DeckSet deckSet : Game.getInstance().getPlayer().getDecks()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/DeckSet.fxml"));
                Parent cardResult = loader.load();
                DeckSetController controller = loader.getController();
                controller.setDeckSet(deckSet);
                controller.setDeckSetName();
                cardResult.setUserData(controller);
                deckList.getItems().add(cardResult);
            } catch (MalformedURLException e) {
            } catch (IOException e) {

            }
        }
    }

    public void createDeck() throws IOException {
        System.out.println("on create");
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deck name");
        dialog.setHeaderText("Entrer deck name");
        dialog.setContentText("the deckName");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            System.out.println("deck name is" + result.get());
            Game.getInstance().getPlayer().getDecks().add(new DeckSet(result.get()));
            Game.save();
            displayListDeck();
        }
    }
}
