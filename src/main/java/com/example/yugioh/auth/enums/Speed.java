package com.example.yugioh.auth.enums;

import lombok.Getter;

@Getter
public enum Speed {
    SPEED1(1),
    SPEED2(2),
    SPEED3(3);

    final int speedLevel;

    Speed(int speedLevel) {
        this.speedLevel = speedLevel;
    }
}
