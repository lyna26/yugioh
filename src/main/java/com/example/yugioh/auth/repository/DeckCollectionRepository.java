package com.example.yugioh.auth.repository;

import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.entity.deck.DeckCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeckCollectionRepository extends JpaRepository<DeckCollection, Integer> {
    List<DeckCollection> findByPlayer(Player player);
}
