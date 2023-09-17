package com.example.yugioh.enums;

public enum CardType {
    MONSTER("Monster"),
    SPELL("Spell"),
    TRAP("Trap");

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}