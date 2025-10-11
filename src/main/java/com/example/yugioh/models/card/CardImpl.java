package com.example.yugioh.models.card;

import com.example.yugioh.enums.Limit;
import com.example.yugioh.exceptions.CardInitializationException;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    final String race;
    final int limit;

    public CardImpl(final ResultSet card) {
        try {
            this.cardId = card.getInt("id");
            this.name = card.getString("name");
            this.description = card.getString("desc");
            this.bigCardImage = card.getString("image_url");
            this.smallCardImage = card.getString("image_url_small");
            this.race = card.getString("race");
            this.limit = Limit.valueOf(card.getString("banlist_info")).getNbCopies();
        }catch(SQLException  | IllegalArgumentException exception){
            log.error("Failed to initialize card from ResultSet", exception);
            throw new CardInitializationException("Failed to initialize card from ResultSet" + exception.getMessage(), exception);
        }
    }
}