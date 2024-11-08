package com.example.yugioh.models.card;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FusionCardImpl extends MonsterCardImpl implements Fusion {

    public FusionCardImpl  (ResultSet card) {
        super(card);
    }
}
