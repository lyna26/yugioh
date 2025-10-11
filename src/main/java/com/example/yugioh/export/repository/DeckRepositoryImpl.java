package com.example.yugioh.export.repository;

import com.example.yugioh.models.card.Card;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * This class is an engine that will communicate with database.
 */
@Slf4j
public class DeckRepositoryImpl {
    public DeckRepositoryImpl() {
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
/*
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
        return cards;*/
        return null;
    }
}