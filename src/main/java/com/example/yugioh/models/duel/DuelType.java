package com.example.yugioh.models.duel;

import com.example.yugioh.enums.PhaseEnum;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@ToString
@Getter
public abstract class DuelType {
    private List<PhaseEnum> phases;
    private int maxLp;

    public DuelType(List<PhaseEnum> phases, int maxLp) {
        this.phases = phases;
        this.maxLp = maxLp;
    }

    public List<PhaseEnum> getPhases() {
        return phases;
    }
}
