package com.example.yugioh.engines;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/**
 * This class is responsible for exporting card data obtained from an external API to the local database.
 * It uses the ApiEngine and DataBaseEngine classes to retrieve and store data, respectively.
 */
public class CardExporterEngine {

    /**
     * This function exports data obtained from the external API to the local database by first retrieving
     * the data using the ApiEngine class and then inserting it into the database using the DataBaseEngine class.
     *
     * @throws SQLException                 if there is an error with the SQL query or connection to the database
     * @throws UnsupportedEncodingException if there is an error with the encoding of the data
     */
    public static void exportApiData() throws SQLException, UnsupportedEncodingException {

        JsonNode cards = ApiEngine.getCardData().get("data");
        DeckRepository.insertCards(cards);
    }


    public static void main(String[] args) throws SQLException, UnsupportedEncodingException {
        exportApiData();
    }
}