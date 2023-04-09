package com.example.yugioh.models.duel;


import com.example.yugioh.models.phase.DrawPhase;
import com.example.yugioh.models.phase.Phase;
import com.example.yugioh.models.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A class representing a single turn in a Yu-Gi-Oh! duel.
 * Each turn belongs to a specific Duel and contains information about the current phase and the player taking the turn.
 */
@Getter
@Setter
@ToString
public class Turn {
    private Phase currentPhase;
    private final Player currentPlayer;

    public Turn(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.currentPhase = new DrawPhase();
    }
}
