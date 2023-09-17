package com.example.yugioh.models.field;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;

public interface Zone {
    void addCard(CardImpl card);

    void removeCard(CardImpl card);
}
