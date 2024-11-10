package com.example.yugioh.engines;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is responsible for exporting card data from API to database
 */
@Slf4j
public class CardExporterEngine {
    private final CardApiService cardApiService ;
    private final CardTransformer cardTransformer ;
    private final DeckRepository deckRepository ;

    public CardExporterEngine() {
        cardApiService = new CardApiService();
        cardTransformer = new CardTransformer();
        deckRepository = new DeckRepository();
    }

    /**
     * Exports data obtained from the external API to the local database
     */
    public void exportApiData(){
        try{
            String cardsJson = cardApiService.fetchAllCards();
            JsonNode cards = cardTransformer.transformToCards(cardsJson);
            deckRepository.insertCards(cards.get("data"));
            log.info("The exportation is a success.");
        } catch (Exception e) {
            log.error("An error occurred while exporting data", e);
        }
    }

    public static void main(String[] args){
        try {
            CardExporterEngine cardExporter = new CardExporterEngine();
            cardExporter.exportApiData();
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}