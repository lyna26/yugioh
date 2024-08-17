package com.example.yugioh.models.card;

public interface Pendulum extends Monster {
    default int getScale() {
        return -1;
    }
}
