package com.example.yugioh.auth.service;

import com.example.yugioh.auth.dto.card.CardDTO;
import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.auth.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Cardservice {
    CardRepository cardRepository;

    public List<CardDTO> findByName(String name){
        List<Card> byName = cardRepository.findByNameContainingIgnoreCase(name);
        //Transformer en DTO et renvoyer
        return null;
    }

}
