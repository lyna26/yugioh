package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;

public class FusionCardImpl extends MonsterCardImpl implements Fusion {
    public FusionCardImpl(final Card card) {
        super(card);
    }
}
