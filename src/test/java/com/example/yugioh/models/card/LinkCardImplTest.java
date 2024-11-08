package com.example.yugioh.models.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LinkCardImplTest {
    private ResultSet QUERY_RESULT;

    @BeforeEach
    void setUp() {
        QUERY_RESULT = mock(ResultSet.class);
    }

    @Test
    void testLinkCardInitialization_Successful() throws SQLException {
        when(QUERY_RESULT.getInt("linkval")).thenReturn(3);
        when(QUERY_RESULT.getString("linkmarkers")).thenReturn("UP, DOWN");

        LinkCardImpl linkCard = new LinkCardImpl(QUERY_RESULT);

        assertEquals(3, linkCard.getLinkRating());
        assertEquals("UP, DOWN", linkCard.getLinkedZones());
        assertEquals(Integer.MIN_VALUE, linkCard.getDef());
    }

    @Test
    void testLinkCardInitialization_Failure() throws SQLException {

        when(QUERY_RESULT.getInt("linkval")).thenThrow(new SQLException("Database error"));
        when(QUERY_RESULT.getString("linkmarkers")).thenReturn("UP, DOWN");

        assertThrows(CardInitializationException.class, () -> {
            new LinkCardImpl(QUERY_RESULT) {};
        });
    }

    @Test
    void when_set_def_value_throw_exception() throws SQLException {
        LinkCardImpl linkMonster = new LinkCardImpl(QUERY_RESULT);

        assertThrows(CardCantHandleActionException.class, () -> {linkMonster.setDef(5);});
    }

    @Test
    void testSetAtkPositiveValue() {
        int validAtk = 2000;
        LinkCardImpl monsterCard = new LinkCardImpl(QUERY_RESULT){};

        monsterCard.setAtk(validAtk);

        assertEquals(validAtk, monsterCard.getAtk());
    }

    @Test
    void testSetAtkNegativeValueThrowsException() {
        int invalidAtk = -100;
       LinkCardImpl monsterCard = new LinkCardImpl(QUERY_RESULT){};


       assertThrows(CantGiveNegativeValue.class, () -> monsterCard.setAtk(invalidAtk));
    }
}