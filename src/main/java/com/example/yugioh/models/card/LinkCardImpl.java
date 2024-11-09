package com.example.yugioh.models.card;

import com.example.yugioh.exceptions.CardCantHandleActionException;
import com.example.yugioh.exceptions.CardInitializationException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;



@Getter
@ToString
@Slf4j
public class LinkCardImpl extends MonsterCardImpl implements Link {
    private final int linkRating;
    private final String linkedZones;

    public LinkCardImpl(final ResultSet card) {
        super(card);
        try {
            this.linkRating = card.getInt("linkval");
            this.linkedZones = card.getString("linkmarkers");
        } catch (SQLException exception) {
            log.error("Failed to initialize Linkcard from ResultSet", exception);
            throw new CardInitializationException("Failed to initialize Linkcard from ResultSet" + exception.getMessage(), exception);
        }
    }

    @Override
    public int getDef(){
        return Integer.MIN_VALUE;
    }

    @Override
    public void setDef(int def){
        throw new CardCantHandleActionException();
    }
}