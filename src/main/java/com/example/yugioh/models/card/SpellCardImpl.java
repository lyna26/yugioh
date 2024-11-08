package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;

@Getter
@Setter
@ToString
public class SpellCardImpl extends CardImpl implements SpellCard{
    public SpellCardImpl(ResultSet card) {
        super(card);
    }

    @Override
    public String getSpellEffect() {
        return null;
    }
}