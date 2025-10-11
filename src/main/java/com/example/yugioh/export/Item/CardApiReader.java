package com.example.yugioh.export.Item;

import com.example.yugioh.export.service.CardApiService;
import com.example.yugioh.export.Mapper.CardTransformer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;

@Component
@Slf4j
public class CardApiReader implements ItemReader<JsonNode> {
    private final CardApiService cardApiService;
    private final CardTransformer cardTransformer;
    private Iterator<JsonNode> iterator;

    public CardApiReader(CardApiService cardApiService, CardTransformer cardTransformer) {
        this.cardApiService = cardApiService;
        this.cardTransformer = cardTransformer;
    }

    @Override
    public JsonNode read() throws IOException, InterruptedException {
        if (iterator == null) {
            String json = cardApiService.fetchAllCards();
            JsonNode cardsNode = cardTransformer.transformToCards(json).get("data");

            if (cardsNode == null || !cardsNode.isArray() || cardsNode.isEmpty()) {
                log.warn("No cards found in API response");
                return null;
            }

            iterator = cardsNode.elements();
            log.info("Total cartes récupérées : {}", cardsNode.size());
        }

        return iterator.hasNext() ? iterator.next() : null;
    }
}
