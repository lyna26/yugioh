package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.game.exceptions.CardCantHandleActionException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Getter
@ToString
@Slf4j
public class LinkCardImpl extends MonsterCardImpl implements Link {
    private final int linkRating;
    private final String linkedZones;

    public LinkCardImpl(final Card card) {
        super(card);
            this.linkRating = card.getLinkVal();
            this.linkedZones = card.getLinkMarkers();
    }

    @Override
    public int getDef(){
        throw new CardCantHandleActionException("this card doesn't have defense");
    }
}