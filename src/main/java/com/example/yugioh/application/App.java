package com.example.yugioh.application;


import com.example.yugioh.models.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException, SQLException {

        Player p = new Player("Lili");
        Game game = Game.getInstance();
        game.setPlayer(p);
        Game.save();
        Game.load();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("yugioh!");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}