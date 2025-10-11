package com.example.yugioh.export.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
public class CardApiService {
    private final String apiUrl;
    private final HttpClient httpClient;

    public CardApiService(@Value("${yugioh.api.url}") String apiUrl) {
        this.apiUrl = apiUrl;

        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        log.info("CardApiService initialized with API URL: {}", apiUrl);
    }

    /**
     * This function will collect all cards data from 'db.ygoprodeck.com/api/v7/cardinfo.php?'
     * @return the result of API research
     */
    public String fetchAllCards() throws IOException, InterruptedException {

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        log.info("Fetching Yu-Gi-Oh cards from {}", apiUrl);

        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        }catch(IOException | InterruptedException e){
            log.error("Failed to fetch cards from API: ", e);
            throw e;
        }
    }
}