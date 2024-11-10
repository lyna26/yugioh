package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.exceptions.CantRemoveCardFromDeckException;
import com.example.yugioh.exceptions.DeckTypeNullException;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.card.SimpleMonster;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeckImplTest {
    @Test
    void should_create_deck_with_correct_min_max_and_emptyList() {
        //Given
        DeckType side = DeckType.SIDE;

        //When
        DeckImpl deck = new DeckSide();

        //Then
        assertEquals(side.name(), deck.getType());
        assertEquals(side.getMaxCard(), deck.getMaxCard());
        assertEquals(side.getMinCard(), deck.getMinCard());
        assertTrue(deck.getCardList().isEmpty());
    }

    @Test
    void should_throw_error_when_deckType_is_null() {
        assertThrows(DeckTypeNullException.class, DeckSide::new);
    }

    @Test
    void should_add_card_to_deck_1() {
        //Given
        int nb_card_min = 1;
        int nb_card_max = 2;

        DeckType mockedType = mock(DeckType.class);
        when(mockedType.getMaxCard()).thenReturn(nb_card_max);
        when(mockedType.getMinCard()).thenReturn(nb_card_min);

        DeckImpl deck = new DeckSide();

        // When
        deck.addCard(mock(SimpleMonster.class));

        // Then
        assertEquals(1, deck.getCardList().size());
    }

    @Test
    void should_add_card_to_deck_2() {
        //Given
        int nb_card_min = 1;
        int nb_card_max = 2;

        DeckType mockedType = mock(DeckType.class);
        when(mockedType.getMaxCard()).thenReturn(nb_card_max);
        when(mockedType.getMinCard()).thenReturn(nb_card_min);

        DeckImpl deck = new DeckSide();

        // When
        deck.addCard(mock(SimpleMonster.class));
        deck.addCard(mock(SimpleMonster.class));
        deck.addCard(mock(SimpleMonster.class));

        // Then
        assertEquals(nb_card_max, deck.getCardList().size());
    }

    @Test
    void should_remove_card_from_deck_1() {
        // Given
        SimpleMonster mockedCard = mock(SimpleMonster.class);

        DeckImpl deck = new DeckSide();
        deck.addCard(mockedCard);

        // When
        deck.removeCard(mockedCard);

        // Then
        assertTrue(deck.getCardList().isEmpty());
    }

    @Test
    void should_throw_exception_when_remove_null_card() {
        // Given
        DeckImpl deck = new DeckMain();

        // Then
        assertThrows(CantRemoveCardFromDeckException.class, () -> deck.removeCard(null));
    }

    @Test
    void should_throw_exception_when_remove_card_not_in_deck() {
        DeckImpl deck = new DeckMain();
        SimpleMonster mockedCard = mock(SimpleMonster.class);

        assertThrows(CantRemoveCardFromDeckException.class, () -> deck.removeCard(mockedCard));
    }

    @Test
    void should_shuffle_deck() {
        //Given
        SimpleMonster card1 = mock(SimpleMonster.class);
        SimpleMonster card2 = mock(SimpleMonster.class);
        SimpleMonster card3 = mock(SimpleMonster.class);

        DeckImpl deck = new DeckMain();
        deck.addCard(card1);
        deck.addCard(card2);
        deck.addCard(card3);

        List<Card> originalCardList = new ArrayList<>(deck.getCardList());

        // When
        deck.shuffle();

        // Then
        assertEquals(originalCardList.size(), deck.getCardList().size());

        // Verify that at least one card has changed its position after shuffling
        boolean orderChanged = false;

        for (int i = 0; i < originalCardList.size(); i++) {
            if (originalCardList.get(i) != deck.getCardList().get(i)) {
                orderChanged = true;
                break;
            }
        }

        assertTrue(orderChanged, "Deck order should change after shuffling.");
    }

    @Test
    void should_be_valid_deck_when_nb_card_between_min_and_max() {

        //Given
        CardImpl card1 = mock(SimpleMonster.class);
        CardImpl card2 = mock(SimpleMonster.class);
        CardImpl card3 = mock(SimpleMonster.class);

        DeckType mockedType = mock(DeckType.class);
        when(mockedType.getMaxCard()).thenReturn(2);
        when(mockedType.getMinCard()).thenReturn(0);

        DeckImpl mockedDeck = mock(DeckImpl.class);

        when(mockedDeck.getCardList()).thenReturn(List.of(card1, card2, card3));

        // When
        boolean isValid = mockedDeck.isValidDeck();

        // Then
        assertFalse(isValid);
    }

    @Test
    void should_not_be_valid_deck_when_nb_card_not_between_min_and_max_1() {


        //Given
        DeckType mockedType = mock(DeckType.class);
        when(mockedType.getMaxCard()).thenReturn(5);
        when(mockedType.getMinCard()).thenReturn(2);

        DeckImpl deck = new DeckMain();

        // Add cards fewer than minimum required
        for (int i = 0; i < mockedType.getMinCard() - 1; i++) {
            deck.addCard(mock(SimpleMonster.class));
        }
        // Test that the deck is invalid when it has fewer than the minimum required cards
        assertFalse(deck.isValidDeck(), "Deck should be invalid when it has fewer than the minimum required cards.");
    }
}