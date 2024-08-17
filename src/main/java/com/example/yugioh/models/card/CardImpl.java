package com.example.yugioh.models.card;

import javafx.scene.image.ImageView;
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
public abstract class CardImpl extends ImageView implements Serializable, Card{
    @Serial
    private static final long serialVersionUID = 1L;
    int cardId;
    String name;
    String description;
    String bigCardImage;
    String smallCardImage;
    String backImage = "C://Users//Lola//IdeaProjects//demo//src//main//resources//com//example//yugioh//images//Yugioh_Card_Back.jpg";
    String race;

    public CardImpl(ResultSet card) throws SQLException {
        this.cardId = card.getInt("id");
        this.name = card.getString("name");
        this.description = card.getString("desc");
        this.smallCardImage = card.getString("image_url_small");
        this.bigCardImage = card.getString("image_url");
        this.race =  card.getString("race");
    }
}