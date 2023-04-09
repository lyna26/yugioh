package com.example.yugioh.enums;

public enum Limit {

    BANNED(0, "BANNED"),
    LIMITED(1, "LIMITED"),
    SEMI_LIMITED(2, "SEMI_LIMITED"),
    NO_LIMITED(3, "NOT_LIMITED");

    private final int nbCopies;

    private final String limitName;

    Limit(int nbCopies, String limitName) {
        this.nbCopies = nbCopies;
        this.limitName = limitName;
    }

    public int getNbCopies() {
        return nbCopies;
    }

    public String getLimitName() {
        return limitName;
    }
}
