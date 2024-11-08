package com.example.yugioh.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.example.yugioh.cmn.SceneUtility.HEIGHT;
import static com.example.yugioh.cmn.SceneUtility.WIDTH;

@Slf4j
public class App extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage){
        try {
            Game.load();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/yugioh/fxml/cmn/MainMenu.fxml"));
            Parent root = fxmlLoader.load();

            primaryStage.setTitle("yugioh!");
            primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException | ClassNotFoundException e) {
            log.error("Failed to load game" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}