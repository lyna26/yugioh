package com.example.yugioh.auth.dto.deck;


import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.dto.card.ExtraDeckMonster;
import com.example.yugioh.auth.dto.card.MonsterCard;
import com.example.yugioh.auth.entity.deck.Deck;
import lombok.extern.slf4j.Slf4j;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */
@Slf4j
public class DeckMainDTO extends DeckDTO {
    public DeckMainDTO(Deck deck) {
        super(deck);
    }

    @Override
    protected boolean isValidType(CardDTO card) {
        if (card instanceof MonsterCard){
            return !(card instanceof ExtraDeckMonster);
        }
        return true;
    }
}
