package com.example.yugioh.export.Item;

import com.example.yugioh.export.Mapper.CardMapper;
import com.example.yugioh.export.model.CardDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CardJsonProcessor implements ItemProcessor<JsonNode, List<CardDTO>> {
    @Override
    public List<CardDTO> process(@NotNull JsonNode cardNode) throws Exception {
        if (cardNode.isEmpty()) {
            return null;
        }

        JsonNode cardImages = cardNode.path("card_images");

        if (cardImages == null || !cardImages.isArray() || cardImages.isEmpty()) {
            log.debug("No images for card '{}', skipping", cardNode.path("name").asText(null));
            return null; // pas d'images => rien à écrire (comme dans ton code d'origine)
        }

        List<CardDTO> results = new ArrayList<>();

        cardImages.forEach(image -> {
            CardDTO dto = CardMapper.fromJsonNode(cardNode);
            dto.setImage_url(image.path( "image_url").asText());
            dto.setImage_url_small(image.path( "image_url_small").asText());
            results.add(dto);
        });
        return results;
    }
}
