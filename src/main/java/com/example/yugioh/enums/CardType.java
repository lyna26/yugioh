package com.example.yugioh.enums;

import lombok.Getter;

@Getter
public enum CardType {
    MONSTER("Monster"),
    SPELL("Spell"),
    TRAP("Trap");

    private final String type;

    CardType(String type) {
        this.type = type;
    }
}