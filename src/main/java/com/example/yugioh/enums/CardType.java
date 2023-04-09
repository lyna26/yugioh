package com.example.yugioh.enums;

public enum CardType {
    MONSTER("Monster"),
    SPELL("Spell"),
    TRAP("Trap");

    private final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }
}