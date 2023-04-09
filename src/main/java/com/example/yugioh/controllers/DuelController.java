package com.example.yugioh.controllers;

import com.example.yugioh.engines.DuelSimulatorEngine;
import com.example.yugioh.enums.PhaseEnum;
import com.example.yugioh.models.duel.Duel;

import com.example.yugioh.models.duel.Turn;
import com.example.yugioh.models.phase.BattlePhase;
import com.example.yugioh.models.phase.DrawPhase;
import com.example.yugioh.models.phase.EndPhase;
import com.example.yugioh.models.phase.MainPhase;
import com.example.yugioh.models.phase.SpPhase;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DuelController implements Initializable {
    private Duel duel;

    Turn turn;

    @FXML
    VBox buttons;
    @FXML
    Button draw;

    @FXML
    Button sp;
    @FXML
    Button main1;
    @FXML
    Button battle;
    @FXML
    Button main2;

    @FXML
    Button end;

    @FXML
    AnchorPane field;
    @FXML
    FieldController fieldController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        duel = DuelSimulatorEngine.simulateGame();
        duel.play();
        startTurn();
    }
    public void startTurn() {
        fieldController.setField(duel.getCurrentPlayer().getField());
        turn = new Turn(duel.getCurrentPlayer());
        draw.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
        sp.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
        main1.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
    }
    public void handleDrawPhase(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        turn.setCurrentPhase(new DrawPhase());
        turn.getCurrentPhase().play();
        if (!duel.getTurns().isEmpty())
        {
            duel.getCurrentPlayer().draw();
        }
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
        duel.addTurn(turn);
        resetButtons();
        startTurn();
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

    public void resetButtons()
    {
        for (Node button : buttons.getChildren())
        {
            button.setDisable(false);
        }
    }
}