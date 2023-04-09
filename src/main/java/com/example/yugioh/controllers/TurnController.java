package com.example.yugioh.controllers;

import com.example.yugioh.enums.PhaseEnum;
import com.example.yugioh.models.duel.Turn;
import com.example.yugioh.models.phase.BattlePhase;
import com.example.yugioh.models.phase.DrawPhase;
import com.example.yugioh.models.phase.EndPhase;
import com.example.yugioh.models.phase.MainPhase;
import com.example.yugioh.models.phase.SpPhase;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TurnController implements Initializable {
    Turn turn;

    @FXML
    VBox buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("hyg");
    }

    public void handleDrawPhase(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        turn.setCurrentPhase(new DrawPhase());
        turn.getCurrentPhase().play();
    }

    public void handleSpPhase(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        turn.setCurrentPhase(new SpPhase());
        turn.getCurrentPhase().play();
    }

    public void handleMain1Phase(MouseEvent event) {
        turn.setCurrentPhase(new MainPhase(PhaseEnum.MAIN1));
        turn.getCurrentPhase().play();
    }

    public void handleBattlePhase(Event event) {
        Button button = (Button) event.getSource();
        disbaleButtonsBefore(button);
        turn.setCurrentPhase(new BattlePhase());
        turn.getCurrentPhase().play();
    }

    public void handleMain2Phase(Event event) {
        Button button = (Button) event.getSource();
        disbaleButtonsBefore(button);
        turn.setCurrentPhase(new MainPhase(PhaseEnum.MAIN2));
        turn.getCurrentPhase().play();
    }

    public void handleEndPhase(Event event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        disbaleButtonsBefore(button);
        turn.setCurrentPhase(new EndPhase());
    }

    private void disbaleButtonsBefore(Button button) {
        int currentIndex = buttons.getChildren().indexOf(button);
        int beforeIndex = currentIndex--;
        button = (Button) buttons.getChildren().get(beforeIndex);
        while (!button.isDisable()) {
            button.setDisable(true);
            beforeIndex--;
            button = (Button) buttons.getChildren().get(beforeIndex);
        }
    }


}