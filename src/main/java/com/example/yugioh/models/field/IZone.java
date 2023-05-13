package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;

public interface IZone {
    void addCard(int index, Card card);

    void removeCard(Card card);
}
