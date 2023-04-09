package com.example.yugioh.models.field;

import com.example.yugioh.models.player.Player;
import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class Field {
    FieldZone fieldZone;
    MonsterZone monsterZone;
    SpellTrapZone spellTrapZone;
    ExtraDeckZone extraDeckZone;
    MainDeckZone mainDeckZone;
    GraveyardZone graveyardZone;
    BanishZone banishZone;
    HandZone handZone;

    public Field() {
        fieldZone = null;
        monsterZone = new MonsterZone();
        spellTrapZone = new SpellTrapZone();
        extraDeckZone = new ExtraDeckZone();
        mainDeckZone = new MainDeckZone();
        graveyardZone = new GraveyardZone();
        banishZone = new BanishZone();
        handZone = new HandZone();
    }
}
