package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;

public class SynchroCardImpl extends MonsterCardImpl implements Synchro {

    public SynchroCardImpl(final Card card) {
        super(card);
    }
}
