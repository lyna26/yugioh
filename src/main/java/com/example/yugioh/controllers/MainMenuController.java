package com.example.yugioh.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.yugioh.cmn.SceneUtility.HEIGHT;
import static com.example.yugioh.cmn.SceneUtility.WIDTH;


public class MainMenuController {
    public Parent getInterface(String interfacename) throws IOException {
        String interfacePath = "/com/example/yugioh/fxml/" + interfacename;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(interfacePath));
        return fxmlLoader.load();
    }


    @FXML
    private void goToDeckBuilder(ActionEvent event) throws IOException {
        Parent deckMenuView = getInterface("deck/DeckMenu.fxml");
        changeScene(event, deckMenuView);

    }
    private void changeScene(ActionEvent event, Parent newRoot){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(newRoot, WIDTH, HEIGHT));
        stage.show();
    }
}
