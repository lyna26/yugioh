package com.example.yugioh.models.duel;

import com.example.yugioh.enums.DuelResult;
import com.example.yugioh.models.player.Player;

import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Value
@ToString
@Slf4j
public class Duel {
    Player firstPlayer;
    Player secondPlayer;
    List<Turn> turns;
    DuelType duelType;

    public Duel(Player firstPlayer, Player secondPlayer, DuelType duelType) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.duelType = duelType;
        turns = new ArrayList<>();
    }

    /**
     * add a turn to the list of turns. The turns mechanic will help to have a history.
     * @param turn the last turn played
     */
    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    /**
     * actions to do at the beginning of the game :
     * prepare the players for the game.
     */
    public void startNewDuel() {
        log.info("Starting the duel between" + firstPlayer.getName() + "and" + secondPlayer.getName());

        preparePlayers();
    }

    /**
     * Prepares the players for the game by setting their lp and shuffling their decks and filling their hands.
     */
    private void preparePlayers() {
    }


    /**
     * actions to do at the end of the game
     */
    public void endGame() {
        log.info("END OF DUEL");

        log.info("The state of the game is" + getResult());
    }

    /**
     *This function will give the state of the game at the end
     * @return PLAYER1_WIN, PLAYER2_WIN, DRAW.
     */
    public DuelResult getResult() {
        return DuelResult.PLAYER1_WIN;
    }

    public Player getCurrentPlayer() {
        if (turns.size() % 2 == 0) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }

    public Player getOpponentPlayer()
    {
        if (turns.size() % 2 == 0) {
            return secondPlayer;
        } else {
            return firstPlayer;
        }
    }
}
