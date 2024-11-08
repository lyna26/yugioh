package com.example.yugioh.models.card;

import java.sql.ResultSet;

public class XyzCardImpl extends MonsterCardImpl implements Xyz {

    public XyzCardImpl(ResultSet card) {
        super(card);
    }
}
