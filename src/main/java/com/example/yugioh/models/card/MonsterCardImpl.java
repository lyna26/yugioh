package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@Slf4j
public abstract class MonsterCardImpl extends CardImpl implements MonsterCard  {
    private int atk;
    private int def;
    private String attribute;

    public MonsterCardImpl(ResultSet card) {
        super(card);
        try {
            this.atk = card.getInt("atk");
            this.def = card.getInt("def");
            this.attribute = card.getString("attribute");
        } catch (SQLException exception) {
            log.error("Failed to initialize Monster card from ResultSet", exception);
            throw new CardInitializationException("Failed to initialize Monster card from ResultSet" + exception.getMessage(), exception);
        }
    }

    public void setAtk(int atk){
        if (atk < 0){
            log.error("You can't set negative value for attack");
            throw new CantGiveNegativeValue("You can't set negative value for attack");
        }
        this.atk = atk;
    }

    public void setDef(int def){
        if (def < 0){
            log.error("You can't set negative value for defense");
            throw new CantGiveNegativeValue("You can't set negative value for defense");
        }
        this.def = def;
    }
}