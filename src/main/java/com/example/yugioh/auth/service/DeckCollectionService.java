package com.example.yugioh.auth.service;

import com.example.yugioh.auth.dto.deck.DeckCollectionDTO;
import com.example.yugioh.auth.entity.Player;
import com.example.yugioh.auth.entity.deck.DeckCollection;
import com.example.yugioh.auth.repository.DeckCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class DeckCollectionService {
    private final DeckCollectionRepository deckCollectionRepository;

    public void createDeckCollection(final String name, final Player player) {
        DeckCollection deckCollection = new DeckCollection(name, player);
        deckCollectionRepository.save(deckCollection);
    }

    public void deleteDeckCollection(int id, Player player) {
        DeckCollection deck = deckCollectionRepository.findById(id)
                .filter(d -> d.getPlayer().equals(player))
                .orElseThrow(() -> new RuntimeException("Deck non trouvé ou accès refusé"));
        deckCollectionRepository.delete(deck);
    }

    public List<DeckCollectionDTO> getDeckCollections(Player player){
        List<DeckCollection> deckCollections = deckCollectionRepository.findByPlayer(player);
        return deckCollections.stream()
                .map(DeckCollectionDTO::new)
                .toList();
    }

    public void rename(int id, String name, Player player) {
        DeckCollection deckCollection = deckCollectionRepository.findById(id)
                .filter(d -> d.getPlayer().equals(player))
                .orElseThrow(() -> new RuntimeException("Deck non trouvé ou accès refusé"));
        deckCollection.setName(name);
        deckCollectionRepository.save(deckCollection);
    }
}
