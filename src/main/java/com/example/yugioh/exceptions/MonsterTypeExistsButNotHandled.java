package com.example.yugioh.exceptions;

public class MonsterTypeExistsButNotHandled extends RuntimeException {
    public MonsterTypeExistsButNotHandled(String message) {
        super(message);
    }
}
