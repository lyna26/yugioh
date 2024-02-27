package com.example.yugioh.models.card;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A class representing a link monster card in the Yu-Gi-Oh! trading card game.
 * Extends the {@link MonsterCard} class.
 */
@ToString(includeFieldNames = true)
public class LinkMonster extends MonsterCard {
    private final int link;
    private final String linkArraws;

    public LinkMonster(ResultSet card) throws SQLException {
        super(card);
        this.link = card.getInt("linkval");
        this.linkArraws = card.getString("linkmarkers");
    }

    @Override
    public int getLink() {
        return link;
    }

    @Override
    public String getLinkArraws() {
        return linkArraws;
    }
}