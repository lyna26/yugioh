package com.example.yugioh.models.card;

public interface MonsterCard extends Card {
    public int getAtk();
    public int getDef();
    public String getAttribute();
    public void setDef(int def);
    public void setAtk(int atk);
}
