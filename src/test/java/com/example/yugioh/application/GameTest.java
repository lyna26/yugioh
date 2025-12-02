package com.example.yugioh.application;

import com.example.yugioh.game.application.Game;
import com.example.yugioh.auth.entity.Player;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = Game.getInstance();
        Player player = new Player("Don", "hgh", "bghj");
        game.setPlayer(player);
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void testGetInstance() {
        Game instance1 = Game.getInstance();
        Game instance2 = Game.getInstance();
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertEquals(instance1, instance2);
    }
}