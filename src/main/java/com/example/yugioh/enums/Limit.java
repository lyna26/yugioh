package com.example.yugioh.enums;

import lombok.Getter;

@Getter
public enum Limit {

    FORBIDDEN(0, "FORBIDDEN"),
    LIMITED(1, "LIMITED"),
    SEMI_LIMITED(2, "SEMI-LIMITED"),
    NO_LIMITED(3, "NOT-LIMITED");

    private final int nbCopies;

    private final String limitName;

    Limit(int nbCopies, String limitName) {
        this.nbCopies = nbCopies;
        this.limitName = limitName;
    }
}
