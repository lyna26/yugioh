package com.example.yugioh.auth.dto.card;


import com.example.yugioh.auth.entity.Card;

public class SpellCardImpl extends CardImplDTO implements SpellCard {
    public SpellCardImpl(final Card card) {
        super(card);
    }
}