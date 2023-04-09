package com.example.yugioh.models.duel;

import com.example.yugioh.engines.DuelSimulatorEngine;
import com.example.yugioh.enums.DuelResult;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.models.player.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DuelTest {

    private final Player firstPlayer =new Player("Lyna");
    private final Player secondPlayer =new Player("Lili");

    @Test
    void shouldGenerateDuel()
    {
        //When
        Duel duel = new Duel(firstPlayer, secondPlayer);

        //Then
        assertNotNull(duel.getFirstPlayer());
        assertNotNull(duel.getSecondPlayer());
        assertNotNull(duel.getTurns());
    }
    @Test
    void shouldAddTurn() {
        //Given
        Duel duel = new Duel(firstPlayer, secondPlayer);

        //When
        duel.addTurn(new Turn(firstPlayer));

        //Then
        assertEquals(duel.getTurns().size(),1);
    }

    @Test
    void shouldFillPlayerHands() throws SQLException, IOException {
        //Given
        DeckSet main = new DeckSet("kuriboh");

        main.setMainDeck(DuelSimulatorEngine.createDeck("kuriboh"));

        firstPlayer.setDuelDeck(main);
        secondPlayer.setDuelDeck(main);

        Duel duel = new Duel(firstPlayer, secondPlayer);

        //When
        duel.fillPlayerHands();

        //Then
        assertEquals(5, firstPlayer.getField().getHandZone().getCards().size());
        assertEquals(5, secondPlayer.getField().getHandZone().getCards().size());

    }

    @Test
    void shouldsetPlayerLP() {
        //Given
        Duel duel = new Duel(firstPlayer, secondPlayer);

        //When
        duel.setPlayerLP();

        //Then
        assertEquals(8000, duel.getFirstPlayer().getLp());
        assertEquals(8000, duel.getSecondPlayer().getLp());
    }

    @Test
    void shouldDuelOverCaseLpZero() {
        //Given
        Duel duel = new Duel(firstPlayer, secondPlayer);

        //When
        duel.getFirstPlayer().setLp(0);

        //Then
        assertTrue(duel.isPlayerLpDownToZero() && duel.isDuelOver());
    }

    @Test
    void shouldGetFirstPlayer() {
        //When
        Duel duel = new Duel(firstPlayer, secondPlayer);

        //Then
        assertEquals("Lyna", duel.getFirstPlayer().getName());
    }

    @Test
    void shouldGetSecondPlayer() {
        //When
        Duel duel = new Duel(firstPlayer, secondPlayer);
        //Then
        assertEquals("Lili", duel.getSecondPlayer().getName());
    }

    void shouldGetDuelResult() {
        //Given
        Duel duel = new Duel(firstPlayer, secondPlayer);

        //When
        duel.getFirstPlayer().setLp(0);
        DuelResult result = duel.getDuelResult();

        //Then
        assertEquals(DuelResult.PLAYER2_WIN, result);
    }

    //TODO: Implement
    /*
    Test the case where the second player's LP is reduced to zero.

Create a new Duel object with the firstPlayer and secondPlayer.
Reduce the secondPlayer's LP to zero by calling secondPlayer.setLp(0).
Assert that the duel.isPlayerLpDownToZero() method returns true and that duel.isDuelOver() method also returns true.
Assert that the duel.getDuelResult() method returns DuelResult.PLAYER1_WIN.
Test the case where the duel ends in a draw.

Create a new Duel object with the firstPlayer and secondPlayer.
Reduce both players' LP to zero by calling firstPlayer.setLp(0) and secondPlayer.setLp(0).
Assert that the duel.isPlayerLpDownToZero() method returns true and that duel.isDuelOver() method also returns true.
Assert that the duel.getDuelResult() method returns DuelResult.DRAW.
Test the case where a player's deck is empty and they cannot draw a card.

Create a new Duel object with the firstPlayer and secondPlayer.
Set both players' decks to empty by calling firstPlayer.getDuelDeck().getMainDeck().clear() and secondPlayer.getDuelDeck().getMainDeck().clear().
Call duel.fillPlayerHands() method to fill the player hands.
Assert that duel.isDuelOver() method returns true.
Assert that the duel.getDuelResult() method returns DuelResult.PLAYER2_WIN if it was the first player's turn, or DuelResult.PLAYER1_WIN if it was the second player's turn.
By adding these additional test cases, you can further increase the coverage of the Duel class and ensure that it works correctly in all scenarios.
     */
}