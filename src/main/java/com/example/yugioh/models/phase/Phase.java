package com.example.yugioh.models.phase;

import com.example.yugioh.enums.PhaseEnum;


public abstract class Phase implements IPhase {
    private final PhaseEnum phaseName;

    public Phase(PhaseEnum phaseName) {
        this.phaseName = phaseName;
    }

    public PhaseEnum getPhaseName() {
        return phaseName;
    }
}
