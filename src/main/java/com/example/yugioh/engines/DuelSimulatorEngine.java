package com.example.yugioh.engines;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.models.duel.Duel;
import com.example.yugioh.models.duel.MasterDuelType;
import com.example.yugioh.models.player.Player;

import java.sql.SQLException;
import java.util.List;

public class DuelSimulatorEngine {
    public static List<CardImpl> createDeck(String deckName) throws SQLException {
        return DataBaseEngine.selectCardsByName(deckName);
    }

    private DuelSimulatorEngine(){}

    public static Duel simulateGame() {
        Player player = new Player("Lyna");
        Player opponent = new Player("Lili");

        DeckSet playerDeckSet = new DeckSet("deck1");


        DeckSet opponentDeckSet = new DeckSet("deck2");


        player.setDuelDeck(playerDeckSet);
        opponent.setDuelDeck(opponentDeckSet);

        try {
            playerDeckSet.getMainDeck().setCardList(createDeck("kuriboh"));
            opponentDeckSet.getMainDeck().setCardList(createDeck("Kuriboh"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new Duel(player, opponent, new MasterDuelType());
    }
}
