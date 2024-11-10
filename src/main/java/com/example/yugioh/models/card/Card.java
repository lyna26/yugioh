package com.example.yugioh.models.card;


public interface Card {
    int getCardId();
    String getName();
    String getDescription();
    String getSmallCardImage();
    String getBigCardImage();
    int getLimit();
}
