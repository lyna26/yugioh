package com.example.yugioh.models.card;

import com.example.yugioh.enums.Face;
import com.example.yugioh.enums.Position;

import java.util.List;
public interface Card {
    int getCardId();
    String getName();
    String getDescription();
    String getSmallCardImage() ;
    String getBigCardImage() ;
    String  getBackImage();
    Face getFace();
    int getAtk();
    int getDef();
    int getLevel();
    String  getAttribute();
    Position getPosition();
    String getRace();
    List<String> getTypes();

    default int getScale() {
        return -1;
    }
    default int getLink (){
        return -1;
    }
    default String  getLinkArraws(){
        return null;
    }
}
