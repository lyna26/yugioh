package com.example.yugioh.models.card;

import com.example.yugioh.factory.card_factory.MonsterCardFactory;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@ToString
@Slf4j
public class PendulumImpl implements Pendulum {
    private int pendulumScale;
    private MonsterCard monster;

    public PendulumImpl(ResultSet monster, String monsterType) {
        try {
            this.pendulumScale = monster.getInt("scale");
            MonsterCard card = MonsterCardFactory.createCard(monster, monsterType);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getCardId() {
        return monster.getCardId();
    }

    @Override
    public String getName() {
        return monster.getName();
    }

    @Override
    public String getDescription() {
        return monster.getDescription();
    }

    @Override
    public String getSmallCardImage() {
        return monster.getSmallCardImage();
    }

    @Override
    public String getBigCardImage() {
        return monster.getBigCardImage();
    }

    @Override
    public int getAtk() {
        return monster.getAtk();
    }

    @Override
    public int getDef() {
        return monster.getDef();
    }

    @Override
    public String getAttribute() {
        return monster.getAttribute();
    }

    @Override
    public void setDef(int def) {
        monster.setDef(def);
    }

    @Override
    public void setAtk(int atk) {
        monster.setAtk(atk);

    }

    @Override
    public String getSpellEffect() {
        return null;
    }
}
