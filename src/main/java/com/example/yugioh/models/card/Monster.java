package com.example.yugioh.models.card;

import com.example.yugioh.enums.Position;

import java.util.List;

public interface Monster extends Card {
    int getAtk();
    int getDef();
    int getLevel();
    String getAttribute();
}
