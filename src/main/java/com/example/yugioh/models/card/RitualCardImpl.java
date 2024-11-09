package com.example.yugioh.models.card;

import java.sql.ResultSet;

public class RitualCardImpl extends MonsterCardImpl implements Ritual{

    public RitualCardImpl(final ResultSet card) {
        super(card);
    }
}
