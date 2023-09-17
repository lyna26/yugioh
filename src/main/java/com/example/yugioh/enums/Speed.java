package com.example.yugioh.enums;

public enum Speed {
    SPEED1(1),
    SPEED2(2),
    SPEED3(3);

    final int speedLevel;

    Speed(int speedLevel) {
        this.speedLevel = speedLevel;
    }
}
