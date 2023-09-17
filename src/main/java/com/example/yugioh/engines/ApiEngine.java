package com.example.yugioh.engines;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * A utility class that provides a method to retrieve cards data from the Yu-Gi-Oh! Pro Deck API.
 */
public class ApiEngine {

    /**
     * This function will collect all cards data from 'db.ygoprodeck.com/api/v7/cardinfo.php?'
     *
     * @return the result of API research
     */
    public static JsonNode getCardData() {
        try {

            URL url = new URL("https://db.ygoprodeck.com/api/v7/cardinfo.php?");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readTree(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}