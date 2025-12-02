package com.example.yugioh.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class CardApiService {

    private final WebClient webClient;

    public final static int PAGE_SIZE = 100;

    // Ajout d'une constante pour le délai d'attente
    private final static Duration API_TIMEOUT = Duration.ofSeconds(30);

    public CardApiService(@Value("${yugioh.api.url}") String apiUrl, WebClient webClient) {
        this.webClient = webClient;
        log.info("CardApiService initialized with API URL: {}", apiUrl);
    }

    public List<String> fetchCardsPages(int startOffset) {
        List<String> pages = new ArrayList<>();
        int offset = startOffset;

        while (true) {
            log.info("Fetching cards with offset {}", offset);

            int finalOffset = offset;

            // 1. Initialiser le Mono pour la requête
            Mono<String> responseMono = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("offset", finalOffset)
                            .queryParam("num", PAGE_SIZE)
                            .build())
                    .retrieve()

                    // 2. Gestion des Erreurs API (codes 4xx/5xx)
                    // Si l'API retourne une erreur, on log et on retourne un Mono vide au lieu de lever une exception
                    .onStatus(status -> status.isError(), clientResponse -> {
                        log.error("API returned error status {} during fetch at offset {}",
                                clientResponse.statusCode(), finalOffset);
                        return Mono.empty();
                    })

                    .bodyToMono(String.class)

                    // 3. Déplacer l'exécution sur un Scheduler dédié aux tâches bloquantes
                    // Ceci libère le thread principal HTTP/Batch pour éviter le Thread Starvation (Problème 2)
                    .subscribeOn(Schedulers.boundedElastic());

            String response;

            try {
                // 4. Blocage synchrone avec un délai d'attente explicite
                response = responseMono.timeout(API_TIMEOUT).block();
            } catch (Exception e) {
                // 5. Capturer les exceptions : timeout, erreur de connexion, ou exceptions levées par .onStatus()
                // Si une erreur non gérée ou un timeout survient, on log et on arrête la boucle.
                String errorMessage = e instanceof WebClientResponseException
                        ? "HTTP Error: " + ((WebClientResponseException) e).getStatusCode()
                        : e.getMessage();

                log.error("Critical error or timeout during API fetch at offset {}. Stopping fetch. Details: {}",
                        offset, errorMessage);
                break;
            }

            // Si response est null, cela signifie que .onStatus a retourné un Mono.empty() (erreur API gérée)
            if (response == null || response.isEmpty()) {
                log.info("Stopping fetch due to handled API error or empty response at offset {}.", offset);
                break;
            }

            // Les deux vérifications suivantes restent pour valider le contenu JSON
            if (!response.contains("\"data\":")) {
                log.info("No 'data' field found, stopping fetch at offset {}.", offset);
                break;
            }

            // Vérifie si le tableau 'data' est vide (cas où l'API retourne un JSON vide)
            if (response.contains("\"data\":[]")) {
                log.info("Reached the end of the card list (empty data array), stopping fetch.");
                break;
            }

            pages.add(response);
            offset += PAGE_SIZE; // Mise à jour de l'offset pour la prochaine requête
        }
        return pages;
    }
}
