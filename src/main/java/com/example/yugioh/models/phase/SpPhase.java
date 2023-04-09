package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;

/*
Il s’agit d’une phase durant laquelle peuvent se résoudre certains effets qui mentionnent « durant la stanby phase ».
Vous pouvez aussi activer des cartes Magies de jeu rapide ou Piège.
 */
public class SpPhase extends Phase {
    public SpPhase() {
        super(PhaseEnum.STANDBY);
    }

    @Override
    public void play() {
        System.out.println("Sp");
    }
}
