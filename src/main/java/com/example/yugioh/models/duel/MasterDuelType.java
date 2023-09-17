package com.example.yugioh.models.duel;

import com.example.yugioh.enums.PhaseEnum;

import java.util.Arrays;

public class MasterDuelType extends DuelType {

    public MasterDuelType() {
        super(Arrays.asList(
                PhaseEnum.DRAW,
                PhaseEnum.STANDBY,
                PhaseEnum.MAIN1,
                PhaseEnum.BATTLE,
                PhaseEnum.MAIN2,
                PhaseEnum.END
        ), 8000);
    }
}
