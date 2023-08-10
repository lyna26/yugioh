package com.example.yugioh.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException{
        Game.load();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/MainMenu.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("yugioh!");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}