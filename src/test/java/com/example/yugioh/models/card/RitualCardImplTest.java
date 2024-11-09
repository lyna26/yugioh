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

class RitualCardImplTest {
    private ResultSet QUERY_RESULT;

    @BeforeEach
    void setUp() {
        QUERY_RESULT = mock(ResultSet.class);
    }

    @Test
    void when_monster_card_initialization_then_Successful() throws SQLException {

        when(QUERY_RESULT.getInt("id")).thenReturn(1);
        when(QUERY_RESULT.getString("name")).thenReturn("Blue-Eyes White Dragon");
        when(QUERY_RESULT.getString("desc")).thenReturn("A powerful dragon with white scales.");
        when(QUERY_RESULT.getString("image_url_small")).thenReturn("url_to_small_image");
        when(QUERY_RESULT.getString("image_url")).thenReturn("url_to_large_image");
        when(QUERY_RESULT.getInt("atk")).thenReturn(2000);
        when(QUERY_RESULT.getInt("def")).thenReturn(1500);
        when(QUERY_RESULT.getString("attribute")).thenReturn("FIRE");

        RitualCardImpl monsterCard = new RitualCardImpl(QUERY_RESULT) {};

        assertEquals(1, monsterCard.getCardId());
        assertEquals("Blue-Eyes White Dragon", monsterCard.getName());
        assertEquals("A powerful dragon with white scales.", monsterCard.getDescription());
        assertEquals("url_to_small_image", monsterCard.getSmallCardImage());
        assertEquals("url_to_large_image", monsterCard.getBigCardImage());
        assertEquals(2000, monsterCard.getAtk());
        assertEquals(1500, monsterCard.getDef());
        assertEquals("FIRE", monsterCard.getAttribute());
    }

    @Test
    public void when_monster_card_initialization_then_failure_1() throws SQLException {

        when(QUERY_RESULT.getInt("id")).thenThrow(new SQLException("Database error"));

        assertThrows(CardInitializationException.class, () -> new RitualCardImpl(QUERY_RESULT) {});
    }

    @Test
    void when_monster_card_initialization_then_failure_2() throws SQLException {
        when(QUERY_RESULT.getInt("atk")).thenThrow(SQLException.class);
        assertThrows(CardInitializationException.class, () -> new RitualCardImpl(QUERY_RESULT) {});
    }

    @Test
    void when_set_atk_with_positive_value_then_ok() {
        int validAtk = 2000;
        RitualCardImpl monsterCard = new RitualCardImpl(QUERY_RESULT){};

        monsterCard.setAtk(validAtk);

        assertEquals(validAtk, monsterCard.getAtk());
    }

    @Test
    void when_set_atk_with_negative_value_then_throws_exception() {
        int invalidAtk = -100;
        RitualCardImpl monsterCard = new RitualCardImpl(QUERY_RESULT){};

        assertThrows(CantGiveNegativeValueException.class, () -> monsterCard.setAtk(invalidAtk));
    }

    @Test
    void when_set_def_with_positive_value_then_ok() {
        int validDef = 1500;
        RitualCardImpl monsterCard = new RitualCardImpl(QUERY_RESULT){};

        monsterCard.setDef(validDef);

        assertEquals(validDef, monsterCard.getDef());
    }

    @Test
    void when_set_def_with_negative_value_then_throws_exception() {
        int invalidDef = -100;
        RitualCardImpl monsterCard = new RitualCardImpl(QUERY_RESULT){};

        assertThrows(CantGiveNegativeValueException.class, () -> monsterCard.setDef(invalidDef));
    }
}