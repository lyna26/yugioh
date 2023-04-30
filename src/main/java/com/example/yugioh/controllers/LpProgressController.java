package com.example.yugioh.controllers;

import com.example.yugioh.models.player.LpProgressBar;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class LpProgressController implements Initializable {
    @FXML
    ProgressBar lpProgressBar;

    @FXML
    Label lpPoint;

    LpProgressBar lpProgress;

    public LpProgressController() {
        lpProgress = new LpProgressBar(8000);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lpPoint.textProperty().bind(lpProgress.getCurrentLp().asString());
        lpProgressBar.progressProperty().bind(lpProgress.getCurrentLp().divide(lpProgress.getMaxLp().getValue().doubleValue()));
    }
}
