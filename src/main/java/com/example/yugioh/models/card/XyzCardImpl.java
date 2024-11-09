package com.example.yugioh.models.card;

import com.example.yugioh.exceptions.CantGiveNegativeValueException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;

@Getter
@Setter
@Slf4j
public class XyzCardImpl extends MonsterCardImpl implements Xyz {
    int overlayUnit = 0;

    public XyzCardImpl(final ResultSet card) {
        super(card);
    }


    @Override
    public void setOverlayUnit(int overLayUnit){
        if (overLayUnit < 0){
            log.error("You can't set negative value for defense");
            throw new CantGiveNegativeValueException("You can't set negative value for defense");
        }
        this.overlayUnit = overLayUnit;
    }
}
