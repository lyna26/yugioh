package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@ToString
@Slf4j
public class PendulumImpl extends MonsterCardImpl implements Pendulum {
    private final int pendulumScale;

    public PendulumImpl(Card monster) {
        super(monster);
            this.pendulumScale = monster.getScale();
    }

}
