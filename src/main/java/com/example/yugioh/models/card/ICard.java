package com.example.yugioh.models.card;

import com.example.yugioh.enums.Face;
import com.example.yugioh.enums.Position;

import java.util.List;
public interface ICard {
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
}
