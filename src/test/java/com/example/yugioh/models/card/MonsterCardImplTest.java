package com.example.yugioh.models.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MonsterCardImplTest {
    private ResultSet QUERY_RESULT;

    @BeforeEach
    void setUp() {
        QUERY_RESULT = mock(ResultSet.class);
    }

    @Test
    void testMonsterCardInitialization_Successful() throws SQLException {

        when(QUERY_RESULT.getInt("atk")).thenReturn(2000);
        when(QUERY_RESULT.getInt("def")).thenReturn(1500);
        when(QUERY_RESULT.getString("attribute")).thenReturn("FIRE");


        MonsterCardImpl monsterCard = new MonsterCardImpl(QUERY_RESULT) {};

        assertEquals(2000, monsterCard.getAtk());
        assertEquals(1500, monsterCard.getDef());
        assertEquals("FIRE", monsterCard.getAttribute());
    }

    @Test
    void testMonsterCardInitialization_Failure() throws SQLException {
        // Arrange: Simulate a SQLException thrown when trying to get the "atk" field
        when(QUERY_RESULT.getInt("atk")).thenThrow(SQLException.class);
        when(QUERY_RESULT.getInt("def")).thenReturn(1500);
        when(QUERY_RESULT.getString("attribute")).thenReturn("FIRE");

        assertThrows(CardInitializationException.class, () -> {
            new MonsterCardImpl(QUERY_RESULT) {};
        });
    }

    @Test
    void testSetAtkPositiveValue() {
        int validAtk = 2000;
        MonsterCardImpl monsterCard = new MonsterCardImpl(QUERY_RESULT){};

        monsterCard.setAtk(validAtk);

        assertEquals(validAtk, monsterCard.getAtk());
    }

    @Test
    void testSetAtkNegativeValueThrowsException() {
        int invalidAtk = -100;
        MonsterCardImpl monsterCard = new MonsterCardImpl(QUERY_RESULT){};


        assertThrows(CantGiveNegativeValue.class, () -> monsterCard.setAtk(invalidAtk));
    }

    @Test
    void testSetDefPositiveValue() {
        int validDef = 1500;

        MonsterCardImpl monsterCard = new MonsterCardImpl(QUERY_RESULT){};

        monsterCard.setDef(validDef);

        assertEquals(validDef, monsterCard.getDef());
    }

    @Test
    void testSetDefNegativeValueThrowsException() {
        int invalidDef = -100;
        MonsterCardImpl monsterCard = new MonsterCardImpl(QUERY_RESULT){};

        assertThrows(CantGiveNegativeValue.class, () -> monsterCard.setDef(invalidDef));

    }
}