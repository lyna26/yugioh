package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;

public class BattlePhase extends Phase {

    public BattlePhase() {
        super(PhaseEnum.BATTLE);
    }

    @Override
    public void play() {
        System.out.println("BATTLE PHASE...");
    }
}
