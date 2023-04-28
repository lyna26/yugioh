package com.example.yugioh.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Value;

import java.io.IOException;


@Value
public class DuelView {
    String FXML_FILE = "/com/example/yugioh/fxml/Duel.fxml";;
    String TITLE = "Duel";
    Stage stage;

    public DuelView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }
}
