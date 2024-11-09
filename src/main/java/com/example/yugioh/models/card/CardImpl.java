package com.example.yugioh.models.card;

import com.example.yugioh.exceptions.CardInitializationException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class represents a Yu-Gi-Oh card.
 * It contains common attributes and methods that are shared by all types of cards.
 */
@Getter
@ToString
@Slf4j
public abstract class CardImpl implements Serializable, Card{
    @Serial private static final long serialVersionUID = 1L;
    final int cardId;
    final String name;
    final String description;
    final String bigCardImage;
    final String smallCardImage;

    public CardImpl(final ResultSet card) {
        try {
            this.cardId = card.getInt("id");
            this.name = card.getString("name");
            this.description = card.getString("desc");
            this.bigCardImage = card.getString("image_url");
            this.smallCardImage = card.getString("image_url_small");
        }catch(SQLException exception){
            log.error("Failed to initialize card from ResultSet", exception);
            throw new CardInitializationException("Failed to initialize card from ResultSet" + exception.getMessage(), exception);
        }
    }
}