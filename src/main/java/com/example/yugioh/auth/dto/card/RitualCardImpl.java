package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;

public class RitualCardImpl extends MonsterCardImpl implements Ritual {

    public RitualCardImpl(final Card card) {
        super(card);
    }
}
