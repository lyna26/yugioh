package com.example.yugioh.models.card;

public class CantGiveNegativeValue extends RuntimeException {
    public CantGiveNegativeValue(String s) {
        super(s);
    }
}
