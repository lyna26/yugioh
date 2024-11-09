package com.example.yugioh.models.card;

import com.example.yugioh.exceptions.CardInitializationException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@ToString
@Slf4j
public class PendulumImpl extends MonsterCardImpl implements Pendulum {
    private final int pendulumScale;

    public PendulumImpl(ResultSet monster) {
        super(monster);
        try {
            this.pendulumScale = monster.getInt("scale");
        } catch (SQLException exception) {
            log.error("Failed to initialize pendulumCard from ResultSet", exception);
            throw new CardInitializationException("Failed to initialize pendulum card from ResultSet" + exception.getMessage(), exception);
        }
    }
    @Override
    public String getSpellEffect() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
