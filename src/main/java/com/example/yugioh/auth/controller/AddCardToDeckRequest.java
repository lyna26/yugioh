package com.example.yugioh.auth.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCardToDeckRequest {
    int deckId;
    int cardId;
}
