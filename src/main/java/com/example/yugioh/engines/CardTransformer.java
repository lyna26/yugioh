package com.example.yugioh.engines;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CardTransformer {
    private final ObjectMapper objectMapper;

    public CardTransformer() {
        this.objectMapper = new ObjectMapper();
    }

    public JsonNode transformToCards(final String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}