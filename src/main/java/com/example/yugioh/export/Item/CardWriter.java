package com.example.yugioh.export.Item;

import com.example.yugioh.export.repository.DeckRepository;
import com.example.yugioh.export.model.CardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardWriter implements ItemWriter<List<CardDTO>> {
    private final DeckRepository deckRepository;

    @Override
    public void write(Chunk<? extends List<CardDTO>> chunk) throws Exception {
        List<CardDTO> allCards = chunk.getItems().stream()
                .filter(list -> list != null && !list.isEmpty())
                .flatMap(List::stream)
                .toList();


        try {
            deckRepository.saveAll(allCards);
            log.info("✅ {} cartes insérées via JDBC batch", allCards);
        } catch (Exception e) {
            throw e;
        }

    }
}
