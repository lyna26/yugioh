package com.example.yugioh.factory.card_factory;

import com.example.yugioh.enums.MonsterType;
import com.example.yugioh.exceptions.MonsterTypeExistsButNotHandled;
import com.example.yugioh.exceptions.UnknownCardTypeException;
import com.example.yugioh.models.card.*;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;


/**
 * A factory class that create monster cards
 */
@Slf4j
public class MonsterCardFactory implements CardFactory {


    @FunctionalInterface
    private interface MonsterCardCreator{
        MonsterCard createCard(ResultSet resultSet);
    }
    private static final Map<MonsterType, MonsterCardCreator> cardCreators = Map.of(
            MonsterType.NORMAL, SimpleMonster::new,
            MonsterType.EFFECT, SimpleMonster::new,
            MonsterType.FUSION, FusionCardImpl::new,
            MonsterType.SYNCHRO, SynchroCardImpl::new,
            MonsterType.XYZ, XyzCardImpl::new,
            MonsterType.LINK, LinkCardImpl::new,
            MonsterType.RITUAL, RitualCardImpl::new
    );


    /**
     * Creates a MonsterCard based on the data provided in the ResultSet.
     *
     * @param cardData the ResultSet containing card information
     * @return a MonsterCard instance corresponding to the MonsterType
     */
    public MonsterCard createCard(ResultSet cardData) {
        try {

            MonsterType monsterType = parseMonsterType(cardData);

            MonsterCardCreator creator = cardCreators.get(monsterType);

            if (creator == null) {
                throw new MonsterTypeExistsButNotHandled("Unsupported monster type: " + monsterType);
            }

            return creator.createCard(cardData);
        } catch (SQLException e) {
            log.error("SQL error while accessing card data: {}", e.getMessage(), e);
            throw new RuntimeException("Database error during monster card creation", e);
        } catch (UnknownCardTypeException | MonsterTypeExistsButNotHandled e) {
            log.error("Error creating monster card: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error during monster card creation: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error during monster card creation", e);
        }

    }

    /**
     * Parses and retrieves the MonsterType from the ResultSet.
     *
     * @param cardData the ResultSet containing card information
     * @return the MonsterType, or throws an IllegalArgumentException if unrecognized
     * @throws SQLException if accessing the ResultSet fails
     */
    private MonsterType parseMonsterType(ResultSet cardData) throws SQLException {
        String frameType = cardData.getString("frame_type");
        return Optional.ofNullable(frameType)
                .map(String::toUpperCase)
                .map(MonsterType::valueOf)
                .orElseThrow(() -> new UnknownCardTypeException("Unknown frame type: " + frameType));
    }
}