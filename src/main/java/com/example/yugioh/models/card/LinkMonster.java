package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class LinkMonster extends MonsterCard implements Link{
    private final int link;
    private final String linkArrows;

    public LinkMonster(ResultSet card) throws SQLException {
        super(card);
        this.link = card.getInt("linkval");
        this.linkArrows = card.getString("linkmarkers");
    }
}
