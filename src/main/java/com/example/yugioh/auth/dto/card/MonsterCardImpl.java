package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@ToString
@Slf4j
@Getter
public abstract class MonsterCardImpl extends CardImplDTO implements MonsterCard {
    private final int atk;
    private final int def;
    private final String attribute;

    public MonsterCardImpl(final Card card) {
        super(card);
        this.atk = card.getAtk();
        this.def = card.getDef();
        this.attribute = card.getAttribute();
    }
}