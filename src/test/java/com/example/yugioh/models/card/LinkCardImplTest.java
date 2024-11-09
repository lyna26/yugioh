package com.example.yugioh.models.card;

import com.example.yugioh.exceptions.CantGiveNegativeValueException;
import com.example.yugioh.exceptions.CardCantHandleActionException;
import com.example.yugioh.exceptions.CardInitializationException;
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
    void when_Link_initialization_then_successful() throws SQLException {
        when(QUERY_RESULT.getInt("id")).thenReturn(1);
        when(QUERY_RESULT.getString("name")).thenReturn("Blue-Eyes White Dragon");
        when(QUERY_RESULT.getString("desc")).thenReturn("A powerful dragon with white scales.");
        when(QUERY_RESULT.getString("image_url_small")).thenReturn("url_to_small_image");
        when(QUERY_RESULT.getString("image_url")).thenReturn("url_to_large_image");
        when(QUERY_RESULT.getInt("linkval")).thenReturn(3);
        when(QUERY_RESULT.getString("linkmarkers")).thenReturn("UP, DOWN");
        when(QUERY_RESULT.getInt("atk")).thenReturn(2000);
        when(QUERY_RESULT.getInt("def")).thenReturn(0);
        when(QUERY_RESULT.getString("attribute")).thenReturn("FIRE");

        LinkCardImpl linkCard = new LinkCardImpl(QUERY_RESULT);

        assertEquals(1, linkCard.getCardId());
        assertEquals("Blue-Eyes White Dragon", linkCard.getName());
        assertEquals("A powerful dragon with white scales.", linkCard.getDescription());
        assertEquals("url_to_small_image", linkCard.getSmallCardImage());
        assertEquals("url_to_large_image", linkCard.getBigCardImage());
        assertEquals(3, linkCard.getLinkRating());
        assertEquals("UP, DOWN", linkCard.getLinkedZones());
        assertEquals(2000, linkCard.getAtk());
        assertEquals("FIRE", linkCard.getAttribute());
        assertEquals(Integer.MIN_VALUE, linkCard.getDef());
    }

    @Test
    void when_link_initialization_then_failure_1() throws SQLException {
        when(QUERY_RESULT.getInt("linkval")).thenThrow(new SQLException("Database error"));
        assertThrows(CardInitializationException.class, () -> new LinkCardImpl(QUERY_RESULT) {});
    }

    @Test
    void when_link_initialization_then_failure_2() throws SQLException {
        when(QUERY_RESULT.getInt("id")).thenThrow(new SQLException("Database error"));
        assertThrows(CardInitializationException.class, () -> new PendulumImpl(QUERY_RESULT) {});
    }

    @Test
    void when_link_initialization_then_failure_3() throws SQLException {
        when(QUERY_RESULT.getInt("atk")).thenThrow(new SQLException("Database error"));
        assertThrows(CardInitializationException.class, () -> new PendulumImpl(QUERY_RESULT) {});
    }

    @Test
    void when_set_def_value_throw_exception()  {
        LinkCardImpl linkMonster = new LinkCardImpl(QUERY_RESULT);

        assertThrows(CardCantHandleActionException.class, () -> linkMonster.setDef(5));
    }

    @Test
    void when_set_atk_with_positive_value_then_ok() {
        int validAtk = 2000;
        LinkCardImpl monsterCard = new LinkCardImpl(QUERY_RESULT){};
        monsterCard.setAtk(validAtk);

        assertEquals(validAtk, monsterCard.getAtk());
    }

    @Test
    void when_set_atk_with_negative_value_then_throws_exception() {
        int invalidAtk = -100;
        LinkCardImpl monsterCard = new LinkCardImpl(QUERY_RESULT){};

        assertThrows(CantGiveNegativeValueException.class, () -> monsterCard.setAtk(invalidAtk));
    }
}