package com.example.yugioh.models.duel;

import com.example.yugioh.enums.DuelResult;
import com.example.yugioh.models.player.Player;

import lombok.ToString;
import lombok.Value;
import java.util.ArrayList;
import java.util.List;

@Value
@ToString

public class Duel {
    Player firstPlayer;
    Player secondPlayer;
    List<Turn> turns;

    int MAX_LP = 8000;
    /**
     * Constructs a new Duel object with two players and initializes the list of turns to an empty list.
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     */
    public Duel(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
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
     * actions to do at the begging of the game :
     * prepare the players for the game.
     */
    public void play() {
        System.out.println("Starting the duel between" + firstPlayer.getName() + "and" + secondPlayer.getName());
        preparePlayers();
    }

    /**
     * Prepares the players for the game by setting their lp and shuffling their decks and filling their hands.
     */
    public void preparePlayers() {
        shufflePlayersDeck();
        playersPutDecks();
        fillPlayerHands();
    }

    /**
     * Shuffles the main deck of both players.
     */
    public void shufflePlayersDeck() {
        firstPlayer.shuffleDeck();
        secondPlayer.shuffleDeck();
    }

    /**
     * players put theirs decks on the fiel
     */
    public void playersPutDecks(){
        firstPlayer.putMainDeck();
        firstPlayer.putExtraDeck();
        secondPlayer.putMainDeck();
        secondPlayer.putExtraDeck();
    }

    /**
     * fill the hand of both players with 5 cards.
     */
    public void fillPlayerHands() {
        for (int i = 0; i < 5; i++) {
            System.out.println("fill first player hand");
            firstPlayer.draw();
            System.out.println("fill second player hand");
            secondPlayer.draw();
        }
    }

    /**
     * set player lp
     */
    /*public void setPlayerLP() {
        firstPlayer.setLp(MAX_LP);
        secondPlayer.setLp(MAX_LP);
    }*/

    /**
     * actions to do at the end of the game
     */
    public void end() {
        System.out.println("END OF DUEL");
        System.out.println("The state of the game is" + getResult());
    }

    /**
     *This function will give the state of the game at the end
     * @return PLAYER1_WIN, PLAYER2_WIN, DRAW.
     */
    public DuelResult getResult()
    {
        if (isPlayerLpDownToZero()) {
            if (firstPlayer.getLp().getCurrentLp() == secondPlayer.getLp().getCurrentLp()) {
                return DuelResult.DRAW;
            } else if (firstPlayer.getLp().getCurrentLp().getValue() > secondPlayer.getLp().getCurrentLp().getValue()) {
                return DuelResult.PLAYER1_WIN;
            } else {
                return DuelResult.PLAYER2_WIN;
            }
        }
        //TODO: change this to check which player summoned Exodia / its deck is empty
        else if (isExodiaSummoned()) {
            return DuelResult.PLAYER1_WIN;
        }

        //TODO: change this to check which player its deck is empty
        return DuelResult.PLAYER1_WIN;
    }

    /**
     * check if one of the players reaches 0 lp
     * @return boolean
     */
    public boolean isPlayerLpDownToZero(){
        return firstPlayer.getLp().getCurrentLp().getValue()<= 0 || secondPlayer.getLp().getCurrentLp().getValue() <= 0;
    }

    /**
     * check if one of the players summoned Exodia
     * @return boolean
     */
    //TODO
    public boolean isExodiaSummoned(){
        return false;
    }

    /**
     * check if one of the players has an empty mainDeck in the field
     * @return boolean
     */
    //TODO
    public boolean isPlayerMainDeckEmpty(){
        return firstPlayer.getField().getMainDeckZone().getCards().isEmpty() ||
                secondPlayer.getField().getMainDeckZone().getCards().isEmpty();
    }

    /**
     * check if a duel is over :
     * 1) a playe reaches 0 lp
     * 2) exodia is summoned
     * 3) no card can be drawn
     */
    public boolean isDuelOver() {
        return isPlayerMainDeckEmpty() || isExodiaSummoned() || isPlayerLpDownToZero();
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
