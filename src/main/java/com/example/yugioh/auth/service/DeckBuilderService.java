package com.example.yugioh.auth.service;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.dto.deck.DeckDTO;
import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.auth.entity.deck.Deck;
import com.example.yugioh.auth.factory.card.CardFactoryImpl;
import com.example.yugioh.auth.factory.deck.DeckFactoryImpl;
import com.example.yugioh.auth.repository.CardRepository;
import com.example.yugioh.auth.repository.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckBuilderService {

    @Autowired
    private DeckRepository deckRepository;

    @Autowired
    private CardRepository cardRepository;


    public void addCardToDeck(int cardId, int deckId) {
        // 1. Charger l'entité deck depuis la BDD
        Deck deckEntity = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck introuvable"));

        // 2. Charger l'entité card depuis la BDD
        Card cardEntity = cardRepository.findById(cardId);

        // 3. Transformer en CardDTO
        CardDTO cardDTO = CardFactoryImpl.createCard(cardEntity);

        // 4. Créer le bon type de DeckDTO à partir de deckEntity
        DeckDTO deckDTO = DeckFactoryImpl.createDeck(deckEntity);

        // 5. Ajout via la logique métier
        deckDTO.addCard(cardDTO);

        // 6. Si l'ajout n'a pas été autorisé (deck full / mauvaise type), on s'arrête
        if (!deckDTO.getCardList().contains(cardDTO)) {
            throw new RuntimeException("Impossible d'ajouter cette carte : limite ou type incompatible.");
        }

        // 7. Mettre à jour l'entité persistante
        deckEntity.getCardList().add(cardEntity);

        // 8. Sauvegarde BDD
        deckRepository.save(deckEntity);
    }

    public void removeCardToDeck(int cardId, int deckId) {
        // 1. Charger l'entité deck depuis la BDD
        Deck deckEntity = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck introuvable"));

        // 2. Charger l'entité card depuis la BDD
        Card cardEntity = cardRepository.findById(cardId);

        // 3. Transformer en CardDTO
        CardDTO cardDTO = CardFactoryImpl.createCard(cardEntity);

        // 4. Créer le bon type de DeckDTO à partir de deckEntity
        DeckDTO deckDTO = DeckFactoryImpl.createDeck(deckEntity);

        // 5. Ajout via la logique métier
        deckDTO.removeCard(cardDTO);

        // 7. Mettre à jour l'entité persistante
        deckEntity.getCardList().remove(cardEntity);

        // 8. Sauvegarde BDD
        deckRepository.save(deckEntity);
    }
}