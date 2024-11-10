package com.example.yugioh.models.card;

public interface MonsterCard extends Card {
    int getAtk();
    int getDef();
    String getAttribute();
    void setDef(int def);
    void setAtk(int atk);
}
