package com.example.yugioh.models.card;

import lombok.Value;


@Value
public class CardInfo {
    String name;
    String desc;
    int id;
    String imageUrl;

    public CardInfo(Card  card){
        this.name = card.getName();
        this.imageUrl = card.getBigCardImage();
        this.id = card.getCardId();
        this.desc = card.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
