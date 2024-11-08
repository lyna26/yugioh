package com.example.yugioh.models.card;

import java.sql.ResultSet;

public class SynchroCardImpl extends MonsterCardImpl implements Synchro {

    public SynchroCardImpl(ResultSet card) {
        super(card);
    }
}
