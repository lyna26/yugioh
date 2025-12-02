package com.example.yugioh.auth.factory.card;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.entity.Card;

public interface CardFactory {
    CardDTO createCard(Card card);
}
