package com.example.yugioh.models.card;

import java.sql.ResultSet;
public class FusionCardImpl extends MonsterCardImpl implements Fusion {
    public FusionCardImpl  (final ResultSet card) {
        super(card);
    }
}
