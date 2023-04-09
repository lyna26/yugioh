package com.example.yugioh.controllers;

import com.example.yugioh.views.DuelView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainMenuController {
    public Parent getInterface(String interfacename) throws IOException {
        String interfacePath = "/com/example/yugioh/fxml/" + interfacename;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(interfacePath));
        Parent root = fxmlLoader.load();
        return root;
    }

    @FXML
    private void goToDeckBuilder(ActionEvent event) throws IOException {
        Parent view = getInterface("DeckMenu.fxml");
        Scene sc = new Scene(view, 1280, 720);
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(sc);
        s.show();
    }

    @FXML
    private void goToDuel(ActionEvent event) throws IOException {
        DuelView duelView = new DuelView();
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setScene(duelView.getStage().getScene());
        s.show();
    }
}
