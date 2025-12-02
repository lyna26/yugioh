package com.example.yugioh.auth.Item;

import com.example.yugioh.auth.service.CardApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static com.example.yugioh.auth.service.CardApiService.PAGE_SIZE;


@Component
@Slf4j
public class CardApiReader implements ItemReader<JsonNode>, ItemStream {
    private static final String CURRENT_OFFSET_KEY = "card_api_current_offset";
    private final CardApiService cardApiService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Iterator<JsonNode> currentCardIterator;
    private Iterator<String> pagesIterator;
    private int currentOffset = 0; // État à sauvegarder

    // CLÉ: Le CardTransformer est inutile ici et a été retiré.
    public CardApiReader(CardApiService cardApiService) {
        this.cardApiService = cardApiService;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        if (executionContext.containsKey(CURRENT_OFFSET_KEY)) {
            currentOffset = executionContext.getInt(CURRENT_OFFSET_KEY);
            log.info("Resuming job at offset: {}", currentOffset);
        }

        // CLÉ: Lancement de l'appel synchrone fetchCardsPages()
        List<String> pages = cardApiService.fetchCardsPages(currentOffset);
        log.info("Fetched {} pages starting from offset {}", pages.size(), currentOffset);
        this.pagesIterator = pages.iterator();
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        // CLÉ: Sauvegarde de l'offset après la lecture d'une page (incrémentée dans read())
        executionContext.putInt(CURRENT_OFFSET_KEY, currentOffset);
    }

    @Override
    public void close() throws ItemStreamException {
        this.pagesIterator = null;
        this.currentCardIterator = null;
    }

    @Override
    public JsonNode read() throws IOException {
        while (currentCardIterator == null || !currentCardIterator.hasNext()) {
            if (!pagesIterator.hasNext()) return null;

            String pageJson = pagesIterator.next();

            // CLÉ: Avance l'offset pour la REPRISE
            // Ceci indique où le job doit repartir si la transformation ou l'écriture échoue.
            currentOffset += PAGE_SIZE;

            JsonNode rootNode = objectMapper.readTree(pageJson).path("data");

            if (!rootNode.isArray() || rootNode.isEmpty()) {
                if (!rootNode.isArray()) {
                    log.warn("Nœud 'data' non trouvé ou n'est pas un tableau. Ignorer la page.");
                }
                continue;
            }

            currentCardIterator = rootNode.elements();
        }
        return currentCardIterator.next();
    }
}