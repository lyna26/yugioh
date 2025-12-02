package com.example.yugioh.auth.Item;

import com.example.yugioh.auth.Mapper.JsonToCardMapper;
import com.example.yugioh.auth.entity.Card;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CardJsonProcessor implements ItemProcessor<JsonNode, List<Card>> {
    @Override
    public List<Card> process(@NotNull JsonNode cardNode) {
        return JsonToCardMapper.fromJsonNode(cardNode);

    }
}
