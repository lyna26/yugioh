package com.example.yugioh.game.exceptions;

public class MonsterTypeExistsButNotHandled extends RuntimeException {
    public MonsterTypeExistsButNotHandled(String message) {
        super(message);
    }
}
