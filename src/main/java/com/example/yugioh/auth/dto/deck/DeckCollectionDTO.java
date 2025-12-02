package com.example.yugioh.auth.dto.deck;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import lombok.Value;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents a complete deck ( main, side & extra) in the game of Yu-Gi-Oh!.
 */

@Value
public class DeckCollectionDTO {
    int id;
    String name;
    List<DeckDTO> decks;

    public DeckCollectionDTO(DeckCollection deckCollection) {
        id = deckCollection.getId();
        name = deckCollection.getName();
        decks = deckCollection.getDecks().stream().map(deck -> {
                    if (Objects.equals(deck.getType(), "MAIN"))
                        return new DeckMainDTO(deck);
                    else if (Objects.equals(deck.getType(), "EXTRA"))
                        return new DeckExtraDTO(deck);
                    else if (Objects.equals(deck.getType(), "SIDE"))
                        return new DeckSideDTO(deck);
                    else
                        throw new IllegalArgumentException("Unknown deck type: " + deck.getClass());
                })
                .collect(Collectors.toList());
    }
}