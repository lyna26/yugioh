package com.example.yugioh.engines;

import com.example.yugioh.factory.cardFactory.CardFactory;
import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.deck.Deck;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.models.duel.Duel;
import com.example.yugioh.models.field.Field;
import com.example.yugioh.models.player.Player;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DuelSimulatorEngine {
    public static Deck createDeck(String deckName) throws SQLException, IOException {
        Deck deck = new Deck(DeckType.MAIN);
        ResultSet deckCardsRes = DataBaseEngine.selectCards(deckName);
        while (deckCardsRes.next()) {
            deck.getCardList().add(CardFactory.createCard(deckCardsRes));
        }
        return deck;
    }

    public static Duel simulateGame() {
        Player player = new Player("Lyna");
        Player opponent = new Player("Lili");

        DeckSet pDeckSet = new DeckSet("deck1");
        DeckSet opponentDeckSet = new DeckSet("deck2");

        player.getDecks().add(pDeckSet);
        opponent.getDecks().add(opponentDeckSet);

        player.setDuelDeck(player.getDecks().get(0));
        opponent.setDuelDeck(opponent.getDecks().get(0));

        try {
            player.getDuelDeck().setMainDeck(createDeck("kuriboh"));
            opponent.getDuelDeck().setMainDeck(createDeck("kuriboh"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new Duel(player, opponent);
    }
}
