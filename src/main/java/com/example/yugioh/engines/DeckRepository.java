package com.example.yugioh.engines;

import com.example.yugioh.enums.Limit;
import com.example.yugioh.enums.LinkMarker;
import com.example.yugioh.factory.card_factory.CardFactoryImpl;
import com.example.yugioh.models.card.Card;
import com.fasterxml.jackson.databind.JsonNode;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an engine that will communicate with database.
 */
@Slf4j
public class DeckRepository {
   private final static String JDBC_URL =
            "jdbc:sqlserver://MSI;"
                    + "database=yugioh;"
                    + "integratedSecurity=false;"
                    + "user=Lola;"
                    + "password=test123;"
                    + "encrypt=false;"
                    + "sendStringParametersAsUnicode=true;"
                    + "useBulkCopyForBatchInsert=true;"
                    + "CharacterSet=UTF-8";

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(JDBC_URL);
        config.setUsername("Lola");
        config.setPassword("test123");
        config.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Adjust if necessary
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        dataSource = new HikariDataSource(config);
    }

    public DeckRepository() {

    }

    public List<Card> selectCardsByName(String name) {
        String query = "SELECT * FROM card WHERE name LIKE ?";

        return executeQuery(query, ps -> ps.setString(1, '%' + name + '%'));
    }

    public static List<Card> selectCardById(String id) {
        String query = "SELECT * FROM card WHERE id = ?";
        return executeQuery(query, ps -> ps.setString(1, id));
    }

    private static List<Card> executeQuery(String query, ThrowingConsumer<PreparedStatement> preparer) {

        List<Card> cards = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            preparer.accept(ps);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cards.add(CardFactoryImpl.createCard(rs));
                }
            }
        } catch (Throwable e) {
            log.error("Database query failed", e);
        }
        return cards;
    }



    /**
     * This function will do an insert query
     *
     * @param cards data formatted as Json
     */
    public void insertCards(JsonNode cards) throws SQLException {
        String reqParam = "insert into card VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement pstm = connection.prepareStatement(reqParam)
        ) {
            cards.forEach(card -> {
                JsonNode cardImages = card.path("card_images");
                cardImages.forEach(cardImage -> {
                    try {
                        setPreparedStatementBatch(cardImage, pstm, card);
                        pstm.addBatch();
                    } catch (SQLException exceptionSql) {
                        log.error("error happened" + exceptionSql.getMessage());
                        throw new RuntimeException(exceptionSql);
                    }
                });
            });
            pstm.executeBatch();
        } catch (SQLException e) {
            throw new SQLException("Error executing database insert", e);
        }
    }

    private static void setPreparedStatementBatch(JsonNode cardImage, PreparedStatement pstm, JsonNode card) throws SQLException {
        pstm.setInt(1, cardImage.path("id").asInt());
        pstm.setString(2, card.path("name").asText());
        pstm.setString(3, card.path("type").asText());
        pstm.setNString(4, card.path("desc").asText());
        pstm.setInt(5, card.path("atk").asInt());
        pstm.setInt(6, card.path("def").asInt());
        pstm.setInt(7, card.path("level").asInt());
        pstm.setString(8, card.path("race").asText());
        pstm.setString(9, card.path("attribute").asText());
        pstm.setString(10, cardImage.path("image_url").asText());
        pstm.setInt(11, card.path("linkval").asInt());
        pstm.setString(12, linkMarkersAsString(card.path("linkmarkers")));
        pstm.setInt(13, card.path("scale").asInt());
        pstm.setString(14, cardImage.path("image_url_small").asText());
        pstm.setString(15, card.path("frameType").asText());
        pstm.setString(16, getBanList(card.path("banlist_info")));
    }


    private static String linkMarkersAsString(JsonNode markers) {

        if (markers.isEmpty()){
            return "";
        }
        StringBuilder linkMarkersBuilder = new StringBuilder();

        for (JsonNode marker : markers) {
            String arrow = LinkMarker.getArrowForMarker(marker.asText());
            linkMarkersBuilder.append(arrow).append(", ");
        }
        String linkMarkers = linkMarkersBuilder.toString();
        if (!linkMarkers.isEmpty()) {
            linkMarkers = linkMarkers.substring(0, linkMarkers.length() - 2);
        }
        return linkMarkers;
    }

    private static String getBanList(JsonNode banList){
        if (banList.isEmpty()){
            return Limit.NO_LIMITED.getLimitName();
        }
        JsonNode banTcgNode = banList.path("ban_tcg");

        if (banTcgNode.isEmpty()) {
            return Limit.NO_LIMITED.getLimitName();
        }

        return banTcgNode.asText().replace('-', '_').toUpperCase();
    }
}