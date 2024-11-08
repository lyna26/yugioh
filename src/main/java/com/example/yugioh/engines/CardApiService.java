package com.example.yugioh.engines;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class CardApiService {
    /**
     * This function will collect all cards data from 'db.ygoprodeck.com/api/v7/cardinfo.php?'
     * @return the result of API research
     */
    public String fetchAllCards() throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newHttpClient();
        final String API_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php?";
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();
        try {
            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException | InterruptedException e){
            log.error("Failed to fetch cards from API: ", e);
            throw e;
        }
    }
}