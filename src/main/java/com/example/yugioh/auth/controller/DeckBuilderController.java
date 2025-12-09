package com.example.yugioh.auth.controller;

import com.example.yugioh.auth.service.DeckBuilderService;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deckbuilder")
@Value
public class DeckBuilderController {
    DeckBuilderService deckBuilderService;

    @PostMapping("/addCard")
    public ResponseEntity addCardToDeck(@RequestBody final AddCardToDeckRequest request) {
        deckBuilderService.addCardToDeck(request.getCardId(), request.getDeckId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/removeCard")
    public ResponseEntity removeCardToDeck(@RequestBody final AddCardToDeckRequest request) {
        deckBuilderService.removeCardToDeck(request.getCardId(), request.getDeckId());
        return ResponseEntity.ok().build();
    }
}
