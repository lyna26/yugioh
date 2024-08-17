package com.example.yugioh.enums;

import lombok.Getter;

@Getter
public enum DeckType {
    MAIN(40, 60),
    EXTRA(0, 15),
    SIDE(0, 15);

    private final int minCard;
    private final int maxCard;

    DeckType(int minCard, int maxCard) {
        this.minCard = minCard;
        this.maxCard = maxCard;
    }

}
