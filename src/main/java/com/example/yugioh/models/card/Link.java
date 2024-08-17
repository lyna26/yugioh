package com.example.yugioh.models.card;

public interface Link extends Monster {
    default int getLink (){
        return -1;
    }
    default String  getLinkArraws(){
        return null;
    }
}
