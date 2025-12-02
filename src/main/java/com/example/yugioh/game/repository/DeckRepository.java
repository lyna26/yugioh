package com.example.yugioh.game.repository;

import com.example.yugioh.auth.entity.deck.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository  extends JpaRepository<Deck, Integer> {
}
