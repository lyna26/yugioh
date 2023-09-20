package com.example.yugioh.models.card;

import com.example.yugioh.enums.Position;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
public class SpellTrapCard extends CardImpl {

    public SpellTrapCard(ResultSet card) throws SQLException {
        super(card);
    }

    @Override
    public int getAtk() {
        return -1;
    }

    @Override
    public int getDef() {
        return -1;
    }

    @Override
    public int getLevel() {
        return -1;
    }

    @Override
    public String getAttribute() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }
}
