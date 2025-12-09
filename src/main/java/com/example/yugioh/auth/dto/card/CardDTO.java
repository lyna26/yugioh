package com.example.yugioh.auth.dto.card;


public interface CardDTO {
    int getId();
    String getName();
    String getDescription();
    String getImageUrl();
    String getImageUrlSmall();
    String getRace();
    int getLimit();
}
