package com.example.yugioh.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TurnView {
    String FXML_FILE = "/com/example/yugioh/fxml/Turn.fxml";;
    String TITLE = "Tur";
    Stage stage;

    public TurnView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }
}
