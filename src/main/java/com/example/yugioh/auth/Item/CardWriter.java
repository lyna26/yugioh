package com.example.yugioh.auth.Item;


import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.auth.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardWriter implements ItemWriter<List<Card>> {
    private final CardRepository cardRepository;

    @Override
    public void write(Chunk<? extends List<Card>> chunk){
        List<Card> allCards = chunk.getItems().stream()
                .filter(list -> list != null && !list.isEmpty())
                .flatMap(List::stream)
                .toList();
        cardRepository.saveAll(allCards);
    }
}
