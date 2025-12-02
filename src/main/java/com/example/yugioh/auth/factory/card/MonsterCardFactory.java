package com.example.yugioh.auth.factory.card;

import com.example.yugioh.auth.dto.card.*;

import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.auth.enums.MonsterType;
import com.example.yugioh.game.exceptions.MonsterTypeExistsButNotHandled;
import com.example.yugioh.game.exceptions.UnknownCardTypeException;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import static com.example.yugioh.auth.enums.MonsterType.*;


@Slf4j
public class MonsterCardFactory implements CardFactory {

    @FunctionalInterface
    private interface MonsterCardCreator {
        MonsterCard createCard(Card resultSet);
    }

    private static final Map<MonsterType, MonsterCardCreator> cardCreators = new EnumMap<>(MonsterType.class);

    static {
        cardCreators.put(FUSION, FusionCardImpl::new);
        cardCreators.put(XYZ, XyzCardImpl::new);
        cardCreators.put(LINK, LinkCardImpl::new);
        cardCreators.put(NORMAL, NormalMonster::new);
        cardCreators.put(SYNCHRO, SynchroCardImpl::new);
        cardCreators.put(RITUAL, RitualCardImpl::new);
        cardCreators.put(PENDULUM, PendulumImpl::new);
    }


    /**
     * Creates a MonsterCard based on the data provided in the ResultSet.
     *
     * @param cardData the ResultSet containing card information
     * @return a MonsterCard instance corresponding to the MonsterType
     */
    public MonsterCard createCard(Card cardData) {

        if (cardData == null){
            throw new IllegalArgumentException("Card data cannot be null");
        }

        MonsterType monsterType = parseMonsterType(cardData);

        MonsterCardCreator creator = cardCreators.get(monsterType);

        if (creator == null) {
            throw new MonsterTypeExistsButNotHandled("Unsupported monster type: " + monsterType);
        }

        return creator.createCard(cardData);

    }


    /**
     * Parses and retrieves the MonsterType from the ResultSet.
     *
     * @param cardData the ResultSet containing card information
     * @return the MonsterType, or throws an IllegalArgumentException if unrecognized
     */
    private MonsterType parseMonsterType(Card cardData) {
        String frameType = cardData.getFrameType().toUpperCase();

        if (frameType.contains(PENDULUM.name())) {
            return PENDULUM;
        }

        return Optional.of(frameType)
                .map(MonsterType::valueOf)
                .orElseThrow(() -> new UnknownCardTypeException("Unknown frame type: " + frameType));
    }
}