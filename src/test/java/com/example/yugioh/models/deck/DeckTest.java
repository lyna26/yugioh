package com.example.yugioh.models.deck;

import com.example.yugioh.enums.DeckType;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import com.example.yugioh.models.card.NormalMonster;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeckTest {
    @Test
    void should_instanciate_deck_with_correct_min_max_and_emptyList() {

        //Given
        DeckType mockedType = mock(DeckType.class);
        when(mockedType.getMaxCard()).thenReturn(10);
        when(mockedType.getMinCard()).thenReturn(1);

        //When
        Deck deck = new Deck(mockedType);

        //Then
        assertEquals(mockedType.getMaxCard(), deck.getMaxCard());
        assertEquals(mockedType.getMinCard(), deck.getMinCard());
        assertTrue(deck.getCardList().isEmpty());
    }


    @Test
    void should_add_card_to_deck(){

        Deck deck = new Deck(mock(DeckType.class));

        int expectedNbCard = 1;

        // When
        deck.addCard(mock(NormalMonster.class));

        // Then
        assertEquals(expectedNbCard, deck.getCardList().size());
    }

    @Test
    void should_remove_card_from_deck(){
        // Given
        Deck deck = new Deck(DeckType.MAIN);

        NormalMonster mockedCard = mock(NormalMonster.class);

        deck.addCard(mockedCard);

        // When
        deck.removeCard(mockedCard);

        // Then
        assertTrue(deck.getCardList().isEmpty());
    }

    @Test
    void should_shuffle_deck(){
        //Given
        CardImpl card1 = mock(NormalMonster.class);
        CardImpl card2 = mock(NormalMonster.class);

        Deck deck  = new Deck(mock(DeckType.class));
        deck.addCard(card1);
        deck.addCard(card2);

        List<CardImpl> originalCardList = new ArrayList<>(deck.getCardList());

        // When
        deck.shuffle();

        // Then
        assertEquals(originalCardList.size(),deck.getCardList().size());
        assertNotEquals(originalCardList.get(0),deck.getCardList().get(0));
    }

    @Test
    void should_be_valid_deck_when_nb_card_between_min_and_max(){

        //Given
        DeckType mockedType = mock(DeckType.class);
        when(mockedType.getMaxCard()).thenReturn(2);
        when(mockedType.getMinCard()).thenReturn(0);

        CardImpl card1 = mock(NormalMonster.class);
        CardImpl card2 = mock(NormalMonster.class);
        CardImpl card3 = mock(NormalMonster.class);

        Deck mockedDeck = mock(Deck.class);

        when(mockedDeck.getCardList()).thenReturn(List.of(card1, card2, card3));


        // When
        boolean isValid = mockedDeck.isValid();

        // Then
      assertFalse(isValid);
    }
}