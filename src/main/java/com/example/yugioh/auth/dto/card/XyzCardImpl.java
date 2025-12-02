package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class XyzCardImpl extends MonsterCardImpl implements Xyz {
    int overlayUnit = 0;

    public XyzCardImpl(final Card card) {
        super(card);
    }
}
