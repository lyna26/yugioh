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

    public DuelView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }
}
