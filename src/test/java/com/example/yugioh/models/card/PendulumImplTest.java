package com.example.yugioh.models.card;

import com.example.yugioh.exceptions.CantGiveNegativeValueException;
import com.example.yugioh.exceptions.CardInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PendulumImplTest {
    private ResultSet QUERY_RESULT;

    @BeforeEach
    void setUp() {
        QUERY_RESULT = mock(ResultSet.class);
    }

    @Test
    void when_pendulum_initialization_then_successful() throws SQLException {
        when(QUERY_RESULT.getInt("id")).thenReturn(1);
        when(QUERY_RESULT.getString("name")).thenReturn("Blue-Eyes White Dragon");
        when(QUERY_RESULT.getString("desc")).thenReturn("A powerful dragon with white scales.");
        when(QUERY_RESULT.getString("image_url_small")).thenReturn("url_to_small_image");
        when(QUERY_RESULT.getString("image_url")).thenReturn("url_to_large_image");
        when(QUERY_RESULT.getInt("scale")).thenReturn(3);
        when(QUERY_RESULT.getInt("atk")).thenReturn(2000);
        when(QUERY_RESULT.getInt("def")).thenReturn(54);
        when(QUERY_RESULT.getString("attribute")).thenReturn("FIRE");

        PendulumImpl pendulumCard = new PendulumImpl(QUERY_RESULT);

        assertEquals(1, pendulumCard.getCardId());
        assertEquals("Blue-Eyes White Dragon", pendulumCard.getName());
        assertEquals("A powerful dragon with white scales.", pendulumCard.getDescription());
        assertEquals("url_to_small_image", pendulumCard.getSmallCardImage());
        assertEquals("url_to_large_image", pendulumCard.getBigCardImage());
        assertEquals(2000, pendulumCard.getAtk());
        assertEquals("FIRE", pendulumCard.getAttribute());
        assertEquals(54, pendulumCard.getDef());
        assertEquals(3, pendulumCard.getPendulumScale());
    }

    @Test
    void when_pendulum_initialization_then_failure_1() throws SQLException {
        when(QUERY_RESULT.getInt("scale")).thenThrow(new SQLException("Database error"));
        assertThrows(CardInitializationException.class, () -> new PendulumImpl(QUERY_RESULT) {});
    }

    @Test
    void when_pendulum_initialization_then_failure_2() throws SQLException {
        when(QUERY_RESULT.getInt("id")).thenThrow(new SQLException("Database error"));
        assertThrows(CardInitializationException.class, () -> new PendulumImpl(QUERY_RESULT) {});
    }

    @Test
    void when_pendulum_initialization_then_failure_3() throws SQLException {
        when(QUERY_RESULT.getInt("atk")).thenThrow(new SQLException("Database error"));
        assertThrows(CardInitializationException.class, () -> new PendulumImpl(QUERY_RESULT) {});
    }

    @Test
    void when_set_atk_with_positive_value_then_ok() {
        int validAtk = 2000;
        PendulumImpl monsterCard = new PendulumImpl(QUERY_RESULT){};
        monsterCard.setAtk(validAtk);

        assertEquals(validAtk, monsterCard.getAtk());
    }

    @Test
    void when_set_atk_with_negative_value_then_throws_exception() {
        int invalidAtk = -100;
        PendulumImpl monsterCard = new PendulumImpl(QUERY_RESULT){};

        assertThrows(CantGiveNegativeValueException.class, () -> monsterCard.setAtk(invalidAtk));
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

        assertThrows(CantGiveNegativeValueException.class, () -> monsterCard.setDef(invalidDef));
    }
}