package com.example.yugioh.models.card;

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
    @Serial
    private static final long serialVersionUID = 1L;
    int cardId;
    String name;
    String description;
    String bigCardImage;
    String smallCardImage;

    public CardImpl(ResultSet card) {
        try {
            this.cardId = card.getInt("id");
            this.name = card.getString("name");
            this.description = card.getString("desc");
            this.smallCardImage = card.getString("image_url_small");
            this.bigCardImage = card.getString("image_url");
        }catch(SQLException exception){
            log.error("Failed to initialize card from ResultSet", exception);
            throw new CardInitializationException("Failed to initialize card from ResultSet" + exception.getMessage(), exception);
        }
    }
}