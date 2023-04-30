package com.example.yugioh.controllers;

import com.example.yugioh.engines.DuelSimulatorEngine;
import com.example.yugioh.enums.PhaseEnum;
import com.example.yugioh.enums.Position;
import com.example.yugioh.models.card.MonsterCard;
import com.example.yugioh.models.duel.Duel;

import com.example.yugioh.models.duel.Turn;
import com.example.yugioh.models.phase.BattlePhase;
import com.example.yugioh.models.phase.DrawPhase;
import com.example.yugioh.models.phase.EndPhase;
import com.example.yugioh.models.phase.MainPhase;
import com.example.yugioh.models.phase.SpPhase;
import com.example.yugioh.models.player.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class DuelController implements Initializable {
    public AnchorPane lpProgressDuelist;
    public LpProgressController lpProgressDuelistController;
    public AnchorPane lpProgressOpponent;
    public LpProgressController lpProgressOpponentController;

    private Duel duel;
    private Turn turn;
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
    AnchorPane fieldDuelist;
    @FXML
    FieldController fieldDuelistController;
    @FXML
    AnchorPane fieldOpponent;
    @FXML
    FieldController fieldOpponentController;

    private MonsterCard attacker;
    private MonsterCard target;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //TODO  Remove the part where I initialize a duel
        duel = DuelSimulatorEngine.simulateGame();

        duel.getFirstPlayer().setField(fieldDuelistController.getField());
        duel.getSecondPlayer().setField(fieldOpponentController.getField());

        duel.getFirstPlayer().setLp(lpProgressDuelistController.getLpProgress());
        duel.getSecondPlayer().setLp(lpProgressOpponentController.getLpProgress());


        duel.getFirstPlayer().setField(fieldDuelistController.getField());
        duel.getSecondPlayer().setField(fieldOpponentController.getField());
        duel.play();

        duel.getCurrentPlayer().summonMonster((MonsterCard) duel.getCurrentPlayer().getField().getHandZone().getCards().get(0));
        startTurn();

    }
    public void startTurn() {

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
        if (!duel.getTurns().isEmpty()) {
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
        addSelecMonsterHandler(fieldDuelistController);
    }

    public void handleMain2Phase(Event event) {
        Button button = (Button) event.getSource();
        disbaleButtonsBefore(button);
        turn.setCurrentPhase(new MainPhase(PhaseEnum.MAIN2));
        turn.getCurrentPhase().play();
    }

    public void handleEndPhase(Event event){
        Button button = (Button) event.getSource();
        button.setDisable(true);
        disbaleButtonsBefore(button);
        turn.setCurrentPhase(new EndPhase());
        duel.addTurn(turn);
        resetButtons();
        startTurn();
    }

    private void disbaleButtonsBefore(Button button){
        int currentIndex = buttons.getChildren().indexOf(button);
        int beforeIndex = currentIndex--;
        button = (Button) buttons.getChildren().get(beforeIndex);
        while (!button.isDisable()) {
            button.setDisable(true);
            beforeIndex--;
            button = (Button) buttons.getChildren().get(beforeIndex);
        }
    }

    public void resetButtons() {
        for (Node button : buttons.getChildren()) {
            button.setDisable(false);
        }
    }

    public void addSelecMonsterHandler(FieldController fieldController) {
        for (Node pane : fieldController.getMonsterZone().getChildren()) {
            StackPane stackPane = (StackPane) pane;
            Rectangle r = (Rectangle) stackPane.getChildren().get(0);
            r.setStroke(Color.GREEN);
            Button attacker = new Button("Attack");
            attacker.setOnMouseClicked(this::handleMonsterToAttack);
            stackPane.getChildren().add(attacker);
        }
    }

    public void addTargetMonsterHandler(FieldController fieldController) {
        boolean isEmptyField = true;

        //TODO handke+le case empty monsters
        for (Node pane : fieldController.getMonsterZone().getChildren()) {
            StackPane stackPane = (StackPane) pane;
            Rectangle r = (Rectangle) stackPane.getChildren().get(0);
            r.setStroke(Color.GREEN);

            try {
                MonsterCard card = (MonsterCard) stackPane.getChildren().get(1);
                isEmptyField = false;
                card.setOnMouseClicked(this::handleMonsterToTarget);
            }
            catch(IndexOutOfBoundsException exception) {
                System.out.println("no card in the stack");
            }
        }
        if(isEmptyField) {
           target = null;
           int damage = CalculateDamage(target,attacker);
           inflictDamage(damage);
        }
    }

    public void handleMonsterToAttack(MouseEvent event) {
        System.out.println("attack monster");

        Button attackerButton = (Button) event.getSource();
        StackPane stackPane = (StackPane) attackerButton.getParent();
        attacker = (MonsterCard) stackPane.getChildren().get(1);

        Player p = duel.getOpponentPlayer();
        if (p == duel.getFirstPlayer())
        {
            addTargetMonsterHandler(fieldDuelistController);
        }
        else {
            addTargetMonsterHandler(fieldOpponentController);
        }
    }

    public void handleMonsterToTarget(MouseEvent event) {
        System.out.println("target monster");
        target= (MonsterCard) event.getTarget();
        int damage = CalculateDamage(target,attacker);
        inflictDamage(damage);
    }

    public int  CalculateDamage(MonsterCard targetMonster, MonsterCard attackMonster)
    {
        System.out.println("Calculating damage...");

        int damage = 0;

        if (targetMonster == null) {
            damage = attackMonster.getAtk();
        }
        else {
            //caseAttackMonster
            if (targetMonster.getPosition() == Position.ATK) {
                damage = attackMonster.getAtk() - targetMonster.getAtk();
            }
            if(targetMonster.getPosition() == Position.DEF) {
                damage = attackMonster.getAtk() - targetMonster.getDef();
            }
        }
        return damage;
    }

    public void inflictDamage(int damage){
        System.out.println("Inflict damage...");
        if (damage < 0) {
            duel.getCurrentPlayer().getLp().getCurrentLp().set(duel.getCurrentPlayer().getLp().getCurrentLp().getValue() + damage);
        }
        if(damage > 0){
            duel.getOpponentPlayer().getLp().getCurrentLp().set(duel.getOpponentPlayer().getLp().getCurrentLp().getValue() - damage);
        }
    }
}