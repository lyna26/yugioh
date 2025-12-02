package com.example.yugioh.auth.controller;

import com.example.yugioh.auth.service.DeckService;
import lombok.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decks")
@Value
public class DeckController {
    DeckService deckService;
/*
    @PostMapping("/{deckId}/")
    public ResponseEntity<DeckDTO> addCardToDeck(
            @PathVariable int deckId,
            @RequestParam int cardId
    ) {
        DeckDTO updated = deckService.addCardToDeck(deckId, cardId);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{deckId}/cards/{cardId}")
    public ResponseEntity<DeckDTO> removeCardFromDeck(
            @PathVariable int deckId,
            @PathVariable int cardId
    ) {
        DeckDTO updated = deckService.removeCardFromDeck(deckId, cardId);
        return ResponseEntity.ok(updated);
    }*/
}
