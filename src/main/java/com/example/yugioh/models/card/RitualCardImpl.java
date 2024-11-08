package com.example.yugioh.models.card;

import java.sql.ResultSet;

public class RitualCardImpl extends MonsterCardImpl implements Ritual{

    public RitualCardImpl(ResultSet card) {
        super(card);
    }
}
