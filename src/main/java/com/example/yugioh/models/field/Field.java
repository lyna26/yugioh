package com.example.yugioh.models.field;

import com.example.yugioh.models.player.PlayerObserver;
import lombok.ToString;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Value
@ToString
@Slf4j
public class Field implements PlayerObserver {
    SingleCardZone fieldZone;
    List<SingleCardZone> monsterZone;
    List<SingleCardZone> spellTrapZone;
    CardListZone extraDeckZone;
    MainDeckZone mainDeckZone;
    CardListZone graveyardZone;
    CardListZone banishZone;
    CardListZone handZone;


    public Field() {
        fieldZone = new SingleCardZone();
        monsterZone = new ArrayList<>();
        spellTrapZone = new ArrayList<>();
        extraDeckZone = new CardListZone();
        mainDeckZone = new MainDeckZone();
        graveyardZone = new CardListZone();
        banishZone = new CardListZone();
        handZone = new CardListZone();
    }

    /**
     *
     */
    @Override
    public void onDeckShuffled() {
        //updateInet
    }
}