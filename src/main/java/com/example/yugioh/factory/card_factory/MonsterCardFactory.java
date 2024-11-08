package com.example.yugioh.factory.card_factory;

import com.example.yugioh.enums.CardType;
import com.example.yugioh.enums.MonsterType;
import com.example.yugioh.models.card.*;
import com.google.common.base.Function;
import com.google.common.base.Optional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import static com.google.common.base.Enums.getIfPresent;

/**
 * A factory class for creating MonsterCard objects.
 */
public class MonsterCardFactory implements CardFactory {
    private static final Map<MonsterType, Function<ResultSet, MonsterCard>> cardCreators = Map.of(
            //MonsterType.NORMAL, MonsterCardImpl::new,
            //MonsterType.EFFECT, MonsterCardImpl::new,
            MonsterType.FUSION, FusionCardImpl::new,
            MonsterType.SYNCHRO, SynchroCardImpl::new,
            MonsterType.XYZ, XyzCardImpl::new,
            MonsterType.LINK, LinkCardImpl::new,
            MonsterType.RITUAL, RitualCardImpl::new
    );


    @Override
    public MonsterCard createCard(ResultSet card) {
        try {
            String[] typeString = card.getString("type").split(" ");

            // Parcourir chaque type possible et utiliser cardCreators s'il existe un type correspondant
            java.util.Optional<MonsterType> cardTypeOpt = Arrays.stream(typeString)
                    .map(String::toUpperCase)
                    .map(type -> getIfPresent(MonsterType.class, type))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();

            return cardTypeOpt.map(cardType -> {
                Function<ResultSet, MonsterCard> creator = cardCreators.get(cardType);
                if (creator != null) {
                    try {
                        return creator.apply(card);
                    } catch (Exception e) {
                        throw new RuntimeException("Erreur lors de la création de la carte de type " + cardType, e);
                    }
                }
                throw new RuntimeException("Type de carte non pris en charge : " + cardType);
            }).orElseThrow(() -> new RuntimeException("Type de carte non trouvé pour : " + typeString));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static MonsterCard createCard(ResultSet card, String type) {
        try {

            // Parcourir chaque type possible et utiliser cardCreators s'il existe un type correspondant

            MonsterType cardType = getIfPresent(MonsterType.class, type.toUpperCase()).get();

            return cardCreators.get(cardType).apply(card);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}