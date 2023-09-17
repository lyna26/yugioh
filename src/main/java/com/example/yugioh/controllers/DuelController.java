package com.example.yugioh.controllers;

import com.example.yugioh.engines.DuelSimulatorEngine;
import com.example.yugioh.enums.Face;
import com.example.yugioh.enums.PhaseEnum;
import com.example.yugioh.models.card.CardImpl;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

@Slf4j
public class DuelController implements Initializable {

    public AnchorPane lpProgressOpponent;
    public AnchorPane lpProgressDuelist;
    public AnchorPane countDownOpponent;
    public AnchorPane countDownDuelist;
    @FXML
    private LpProgressController lpProgressDuelistController;
    @FXML
    private LpProgressController lpProgressOpponentController;
    @FXML
    private CountDownController countDownDuelistController;
    @FXML
    private CountDownController countDownOpponentController;
    @FXML
    AnchorPane fieldDuelist;
    @FXML
    private FieldController fieldDuelistController;
    @FXML
    private AnchorPane fieldOpponent;
    @FXML
    private FieldController fieldOpponentController;


    private Duel duel;
    private Turn turn;
    private MonsterCard attacker;
    private MonsterCard target;

    private final List<MonsterCard> monstersToTribute = new ArrayList<>();

    private CardImpl monsterToSummon;

    private int nbMonsterToTribute = 0;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        initDuel();
        preparePlayersField();
        preparePlayersLp();
        duel.startNewDuel();
        startTurn();
    }

    private void initDuel(){
        duel = DuelSimulatorEngine.simulateGame();
    }

    private void preparePlayersField(){
        duel.getFirstPlayer().setField(fieldDuelistController.getField());
        duel.getSecondPlayer().setField(fieldOpponentController.getField());
    }

    private void preparePlayersLp(){
        duel.getFirstPlayer().setLp(lpProgressDuelistController.getLpProgress());
        duel.getSecondPlayer().setLp(lpProgressOpponentController.getLpProgress());
    }

    private void startTurn() {
        turn = new Turn(duel.getCurrentPlayer());
        draw.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
        sp.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
        main1.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
    }

    /**
     Checks if it is the first turn in a duel.
     @return {boolean} true if it is the first turn, false otherwise.
     */
    private boolean isFirstTurn(){
        return !duel.getTurns().isEmpty();
    }

    private void disableButtonsBefore(Button button){
        int currentIndex = buttons.getChildren().indexOf(button);
        int beforeIndex = currentIndex--;
        button = (Button) buttons.getChildren().get(beforeIndex);
        while (!button.isDisable()) {
            button.setDisable(true);
            beforeIndex--;
            button = (Button) buttons.getChildren().get(beforeIndex);
        }
    }


    public void handleDrawPhase(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        turn.setCurrentPhase(new DrawPhase());
        if (isFirstTurn()) {
            turn.getDrawPhase().draw(duel.getCurrentPlayer());
        }
    }

    public void handleSpPhase(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setDisable(true);
        turn.setCurrentPhase(new SpPhase());
        turn.getCurrentPhase().play();
    }

    public void handleMain1Phase(MouseEvent event) {
        countDownDuelistController.startTimer();
        turn.setCurrentPhase(new MainPhase(PhaseEnum.MAIN1));
        turn.getCurrentPhase().play();
        //Add summon button
        addSummonMonsterHandler(fieldDuelistController);

        addSetCardHandler(fieldDuelistController);
    }

    public void handleBattlePhase(Event event) {
        Button button = (Button) event.getSource();
        disableButtonsBefore(button);
        turn.setCurrentPhase(new BattlePhase());
        turn.getCurrentPhase().play();
        addSelectMonsterHandler(fieldDuelistController);
    }

    public void handleMain2Phase(Event event) {
        Button button = (Button) event.getSource();
        disableButtonsBefore(button);
        turn.setCurrentPhase(new MainPhase(PhaseEnum.MAIN2));
        turn.getCurrentPhase().play();
    }

    public void handleEndPhase(Event event){
        Button button = (Button) event.getSource();
        button.setDisable(true);
        disableButtonsBefore(button);
        turn.setCurrentPhase(new EndPhase());
        duel.addTurn(turn);
        resetButtons();
        startTurn();
    }

    private void resetButtons() {
        for (Node button : buttons.getChildren()) {
            button.setDisable(false);
        }
    }

    /**
     * Adds an event handler to each monster on the field, that let the player choose a monster that will attack.
     * @param fieldController
     */
    private void addSelectMonsterHandler(FieldController fieldController) {
        for (Node pane : fieldController.getMonsterZone().getChildren()) {
            StackPane stackPane = (StackPane) pane;
            Rectangle r = (Rectangle) stackPane.getChildren().get(0);
            r.setStroke(Color.GREEN);
            Button attacker = new Button("Attack");
            attacker.setOnMouseClicked(this::handleMonsterToAttack);
            stackPane.getChildren().add(attacker);
        }
    }

    /**
     * Adds an event handler to each monster on the hand, that let the player summon a monster
     * @param fieldController
     */
    private void addSummonMonsterHandler(FieldController fieldController) {
        for (Node pane : fieldController.getHandZone().getChildren()) {
            Button summon = new Button("Summon");
            summon.setOnMouseClicked(this::handleSummon);
            StackPane stackPane = (StackPane) pane;
            CardImpl card = (CardImpl) stackPane.getChildren().get(0);
            if(card.getTypes().contains("Monster")){
                stackPane.getChildren().add(1, summon);
            }
        }
    }

    /**
     * Adds an event handler to each card on the hand, that let the player put a card in face down position on the field.
     * @param fieldController
     */
    private void addSetCardHandler(FieldController fieldController) {
        for (Node pane : fieldController.getHandZone().getChildren()) {
            Button set = new Button("Set");
            set.setOnMouseClicked(this::handleSet);
            StackPane stackPane = (StackPane) pane;
            stackPane.getChildren().add(2, set);
        }
    }


    private int getNumberOfMonsterToTribute(MonsterCard monster){
        if(monster.getLevel() <= 4) {
            return 0;
        }
        else if(monster.getLevel() <=6){
            return 1;
        }
        else{
            return 2;
        }
    }

    private void handleSet(MouseEvent event) {
        Button setButton = (Button) event.getSource();
        StackPane stackPane = (StackPane) setButton.getParent();
        CardImpl toSet = (CardImpl) stackPane.getChildren().get(0);
        toSet.setFace(Face.DOWN);
        monsterToSummon = toSet;

        if (monsterToSummon.getTypes().contains("Monster")) {
            summonStep();
        }
        else {
            addChooseZoneSpellTrapZone(fieldDuelistController);
        }
    }

    private void handleSummon(MouseEvent event) {
        Button summonButton = (Button) event.getSource();
        StackPane stackPane = (StackPane) summonButton.getParent();
        MonsterCard summon = (MonsterCard) stackPane.getChildren().get(0);
        monsterToSummon = summon;
        nbMonsterToTribute = getNumberOfMonsterToTribute(summon);
        summonStep();
    }

    private void summonStep() {
        if(nbMonsterToTribute == 0) {
            addChooseZoneMonsterZone(fieldDuelistController);
        }
        else {
            if (duel.getCurrentPlayer() == duel.getFirstPlayer()) {
                chooseMonster(fieldDuelistController);
            } else {
                chooseMonster(fieldOpponentController);
            }
        }
    }

    /**
     * Adds an event handler to each monster on the field, that let the player choose a zone where to summon a monster
     * @param fieldController
     */
    private void addChooseZoneMonsterZone(FieldController fieldController) {
        for (Node pane : fieldController.getMonsterZone().getChildren()) {
            StackPane stackPane = (StackPane) pane;
            Rectangle r = (Rectangle) stackPane.getChildren().get(0);
            r.setStroke(Color.RED);
            r.setOnMouseClicked(this::handleZoneToChoose);
        }
    }

    /**
     * Adds an event handler to each monster on the field, that let the player choose a zone where to summon a monster
     * @param fieldController
     */
    private void addChooseZoneSpellTrapZone(FieldController fieldController) {
        for (Node pane : fieldController.getSpellTrapZone().getChildren()) {
            StackPane stackPane = (StackPane) pane;
            Rectangle r = (Rectangle) stackPane.getChildren().get(0);
            r.setStroke(Color.RED);
            r.setOnMouseClicked(this::handleZoneToChoose);
        }
    }

    private void handleZoneToChoose(MouseEvent event) {
        Rectangle r = (Rectangle) event.getSource();
        StackPane stackPane = (StackPane) r.getParent();

        if (monsterToSummon.getTypes().contains("Monster"))
        {
            //duel.getCurrentPlayer().getField().putCardOnMonsterZone(stackPane.getParent().getChildrenUnmodifiable().indexOf(stackPane), (MonsterCard) monsterToSummon);
        }
        else
        {
           // duel.getCurrentPlayer().putCardOnTheSpellTrapZone(stackPane.getParent().getChildrenUnmodifiable().indexOf(stackPane), monsterToSummon);
        }

    }

    private void chooseMonster(@NotNull FieldController controller) {
        for (Node pane : controller.getMonsterZone().getChildren()) {
            StackPane stackPane = (StackPane) pane;
            Rectangle r = (Rectangle) stackPane.getChildren().get(0);
            r.setStroke(Color.RED);

            try {
                MonsterCard card = (MonsterCard) stackPane.getChildren().get(1);
                card.setOnMouseClicked(this::handleMonsterToSummon);
            }
            catch(IndexOutOfBoundsException exception){
                log.warn("No card in the stack" + exception);
            }
            catch(ClassCastException exception){
                log.warn("Wrong cast" + exception);
            }
        }
    }

    /**
     * Handles the summon event
     * @param event
     */
    private void handleMonsterToSummon(MouseEvent event) {
        MonsterCard monster = (MonsterCard) event.getSource();

        monstersToTribute.add(monster);

        //Can summon
        if (monstersToTribute.size() == nbMonsterToTribute) {
            addChooseZoneMonsterZone(fieldDuelistController);
        }
    }


    /**
     * Let the current duelist choose the monster that will attach, then the monster to target if there is monsters on the opponent field.
     * @param fieldController field controller
     */
    private void addTargetMonsterHandler(@NotNull FieldController fieldController) {
        boolean isEmptyField = true;

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
                log.warn("No card in the stack");
            }
        }
        if(isEmptyField) {
           target = null;
           int damage = turn.getBattlePhase().CalculateDamage(null,attacker);
           Player inflictDamage = getPlayerToInflictDamage(damage);
           turn.getBattlePhase().inflictDamage(damage, inflictDamage);
        }
    }

    /**
     *
     * @param damage The lp damage that a player will receive
     * @return The player that will receive the calculated lp damage
     */
    private Player getPlayerToInflictDamage(int damage) {
        if(damage < 0){
            return this.duel.getCurrentPlayer();
        }
        return duel.getOpponentPlayer();
    }

    /**
     * Handles the event of selecting a monster to attack.
     * This method is invoked when the "Attack" button associated with a monster is clicked.
     * @param  event - The mouse event generated by clicking the "Attack" button.
     */
    private void handleMonsterToAttack(MouseEvent event) {
        Button attackerButton = (Button) event.getSource();
        StackPane stackPane = (StackPane) attackerButton.getParent();
        attacker = (MonsterCard) stackPane.getChildren().get(1);
        Player p = duel.getOpponentPlayer();
        if (p == duel.getFirstPlayer()) {
            addTargetMonsterHandler(fieldDuelistController);
        }
        else {
            addTargetMonsterHandler(fieldOpponentController);
        }
    }

    /**
     * Handles the event of selecting a target monster for an attack.
     * This method is called when a monster is selected as the target for an attack.
     * It retrieves the target monster from the event and calculates the damage to be inflicted
     * @param event - The mouse event triggered by selecting a target monster.
     */
    private void handleMonsterToTarget(MouseEvent event) {
        target= (MonsterCard) event.getTarget();
        int damage = turn.getBattlePhase().CalculateDamage(target,attacker);
        turn.getBattlePhase().inflictDamage(damage, getPlayerToInflictDamage(damage));
    }
}
