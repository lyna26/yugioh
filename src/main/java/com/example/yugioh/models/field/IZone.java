package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;

public interface IZone {
    void addCard(Card card);

    void removeCard(Card card);
}
