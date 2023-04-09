package com.example.yugioh.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleFieldController implements Initializable {
    @FXML
    AnchorPane opponentField;

    @FXML
    AnchorPane duelistField;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        opponentField.setScaleY(-1);
    }
}
