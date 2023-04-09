package com.example.yugioh.models.card;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardInfo {
    private final String name;
    private final String desc;
    private final String race;
    private final int atk;
    private final int def;
    private final int level;
    private final int id;
    private final String imageUrl;

    public CardInfo(ResultSet card) throws SQLException {
        this.name = card.getString("name");
        this.imageUrl = card.getString("image_url");
        this.id = card.getInt("id");
        this.desc = card.getString("desc");
        this.level = card.getInt("level");
        this.race = card.getString("race");
        this.atk = card.getInt("atk");
        this.def = card.getInt("def");
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getRace() {
        return race;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.getRace() + "\n" + this.desc;
    }
}
