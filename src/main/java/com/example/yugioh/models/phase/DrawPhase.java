package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;
import com.example.yugioh.models.player.Player;
import lombok.extern.slf4j.Slf4j;


/**
 * This class represents the draw phase of yugioh! game
 */
public class DrawPhase extends Phase {
    public DrawPhase() {
        super(PhaseEnum.DRAW);
    }

    @Override
    public void play() {
        System.out.println("DRAW PHASE");
        //TODO make the player draw
    }

    /**
     * This methode will make the current user draw a card
     * @param drawer the current duelist that will draw a card
     */
    public void draw(Player drawer){
        drawer.draw();
    }
}
