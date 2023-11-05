package com.example.yugioh.engines;

import com.example.yugioh.enums.LinkMarker;
import com.example.yugioh.factory.card_factory.CardFactoryImpl;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.CardImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an engine that will communicate with database.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class DataBaseEngine {

    public static List<CardImpl> selectCardsByName(String name) throws SQLException {
        try (
                Connection connection = connect();
                PreparedStatement pstm = connection.prepareStatement("SELECT * FROM card WHERE name LIKE ?")
        ) {
            pstm.setString(1, '%' + name + '%');
            ResultSet res = pstm.executeQuery();
            List<CardImpl> cards = new ArrayList<>();
            while (res.next()) {
                cards.add(CardFactoryImpl.createCard(res));
            }
            return cards;

        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static List<CardImpl> selectCardById(String id) throws SQLException {
        try(
                Connection connection = connect();
                PreparedStatement pstm = connection.prepareStatement("SELECT * FROM card WHERE id = ?")
        ){
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            List<CardImpl> results = new ArrayList<CardImpl>();
            while(res.next()) {
                results.add(CardFactoryImpl.createCard(res));
            }
            return results;
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private static Connection connect() throws SQLException {
        String url =
                "jdbc:sqlserver://MSI;"
                        + "database=yugioh;"
                        + "integratedSecurity=false;"
                        + "user=Lola;"
                        + "password=Test1234;"
                        + "encrypt=false;"
                        + "sendStringParametersAsUnicode=true;"
                        + "useBulkCopyForBatchInsert=true;"
                        + "CharacterSet=UTF-8";
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new SQLException("Failed to connect to the database.", e);
        }
    }

    /**
     * This function will do an insert query
     * @param cards data formatted as Json
     */
    public static void insertCards(JsonNode cards) throws SQLException {
        if (cards == null) {
            String msg = "The cards parameter cannot be null.";
            log.error(msg);
            throw new IllegalArgumentException(msg);
        }

        String reqParam = "insert into card VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (
                Connection connection = connect();
                PreparedStatement pstm = connection.prepareStatement(reqParam)
        ) {
            cards.forEach(card -> {
                JsonNode cardImages = card.path("card_images");
                Charset def = Charset.defaultCharset();
                byte[] bytes = card.path("desc").asText().getBytes(StandardCharsets.UTF_8);

                cardImages.forEach(node -> {
                    try {
                        pstm.setInt(1, node.path("id").asInt());
                        pstm.setString(2, card.path("name").asText());
                        pstm.setString(3, card.path("type").asText());

                        String desc = new String(bytes, def);
                        pstm.setNString(4, desc);

                        pstm.setInt(5, card.path("atk").asInt());
                        pstm.setInt(6, card.path("def").asInt());
                        pstm.setInt(7, card.path("level").asInt());
                        pstm.setString(8, card.path("race").asText());
                        pstm.setString(9, card.path("attribute").asText());
                        pstm.setString(10, node.path("image_url").asText());
                        pstm.setInt(11, card.path("linkval").asInt());

                        StringBuilder linkMarkersBuilder = new StringBuilder();
                        JsonNode markers = card.path("linkmarkers");
                        for (JsonNode marker : markers) {
                            String arrow = LinkMarker.getArrowForMarker(marker.asText());
                            linkMarkersBuilder.append(arrow).append(", ");
                        }
                        String linkMarkers = linkMarkersBuilder.toString();
                        if (linkMarkers.length() > 0) {
                            linkMarkers = linkMarkers.substring(0, linkMarkers.length() - 2);
                        }

                        pstm.setString(12, linkMarkers);

                        pstm.setString(12, linkMarkers);

                        pstm.setInt(13, card.path("scale").asInt());
                        pstm.setString(14, node.path("image_url_small").asText());
                        pstm.addBatch();
                    } catch (SQLException exceptionSql) {
                        log.error("error happened");
                    }
                });
            });
            pstm.executeBatch();
        } catch (SQLException e) {
            throw new SQLException("Error executing database insert", e);
        }
    }
}