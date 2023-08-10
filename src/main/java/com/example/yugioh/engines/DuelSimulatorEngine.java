package com.example.yugioh.engines;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.deck.Deck;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.models.duel.Duel;
import com.example.yugioh.models.player.Player;

import java.sql.SQLException;
import java.util.List;

public class DuelSimulatorEngine {
    public static Deck createDeck(String deckName) throws SQLException{
        Deck deck = new Deck(DeckType.MAIN);
        List<Card> deckCardsRes = DataBaseEngine.selectCards(deckName);
        for(Card card : deckCardsRes){
            deck.getCardList().add(card);
        }
        return deck;
    }

    private DuelSimulatorEngine(){}

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
        }
        return new Duel(player, opponent);
    }
}
