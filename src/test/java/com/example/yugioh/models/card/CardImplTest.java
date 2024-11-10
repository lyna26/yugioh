package com.example.yugioh.models.card;

import com.example.yugioh.enums.Limit;
import com.example.yugioh.exceptions.CardInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CardImplTest {

    private ResultSet QUERY_RESULT;

    @BeforeEach
    void setUp() {
        QUERY_RESULT = Mockito.mock(ResultSet.class);
    }

    @Test
    void init_card_when_getting_result_is_correct() throws SQLException {
        when(QUERY_RESULT.getInt("id")).thenReturn(1);
        when(QUERY_RESULT.getString("name")).thenReturn("Blue-Eyes White Dragon");
        when(QUERY_RESULT.getString("desc")).thenReturn("A powerful dragon with white scales.");
        when(QUERY_RESULT.getString("image_url_small")).thenReturn("url_to_small_image");
        when(QUERY_RESULT.getString("image_url")).thenReturn("url_to_large_image");
        when(QUERY_RESULT.getString("ban")).thenReturn("FORBIDDEN");

        final CardImpl card = new CardImpl(QUERY_RESULT) {
        };

        assertEquals(1, card.getCardId());
        assertEquals("Blue-Eyes White Dragon", card.getName());
        assertEquals("A powerful dragon with white scales.", card.getDescription());
        assertEquals("url_to_small_image", card.getSmallCardImage());
        assertEquals("url_to_large_image", card.getBigCardImage());
        assertEquals(Limit.FORBIDDEN.getNbCopies(), card.getLimit());
    }

    @Test
    void init_card_when_getting_result_is_wrong() throws SQLException {
        when(QUERY_RESULT.getInt("id")).thenReturn(1);
        when(QUERY_RESULT.getString("name")).thenReturn("Blue-Eyes White Dragon");
        when(QUERY_RESULT.getString("desc")).thenReturn("A powerful dragon with white scales.");
        when(QUERY_RESULT.getString("image_url_small")).thenReturn("url_to_small_image");
        when(QUERY_RESULT.getString("image_url")).thenReturn("url_to_large_image");
        when(QUERY_RESULT.getString("ban")).thenReturn("FOR");

        assertThrows(CardInitializationException.class, () -> new CardImpl(QUERY_RESULT) {});
    }


    @Test
    public void init_card_when_sql_error_throws_exception() throws SQLException {

        when(QUERY_RESULT.getInt("id")).thenThrow(new SQLException("Database error"));

        assertThrows(CardInitializationException.class, () -> new CardImpl(QUERY_RESULT) {
        });
    }
}