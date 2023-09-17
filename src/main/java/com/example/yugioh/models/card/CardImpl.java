package com.example.yugioh.models.card;

import com.example.yugioh.enums.Face;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * This abstract class represents a Yu-Gi-Oh card.
 * It contains common attributes and methods that are shared by all types of cards.
 */
@Getter
@Setter
@ToString
@Slf4j
public abstract class CardImpl extends ImageView implements Serializable, Card {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int cardId;
    private final String name;
    private final String description;
    private final String bigCardImage;
    private final String smallCardImage;
    private String backImage = "C://Users//Lola//IdeaProjects//demo//src//main//resources//com//example//yugioh//images//Yugioh_Card_Back.jpg";
    private final List<String> types;
    private final String monsterRace;
    private Face face = Face.UP;

    public CardImpl(ResultSet card) throws SQLException {
        this.cardId = card.getInt("id");
        this.name = card.getString("name");
        this.description = card.getString("desc");
        this.smallCardImage = card.getString("image_url_small");
        this.bigCardImage = card.getString("image_url");
        this.monsterRace =  card.getString("race");
        this.types = parseTypeList(card.getString("type"));
    }

    /**
     * Parses a string of types and sets the types of this card accordingly.
     * The types are expected to be separated by a space character.
     * @param types a string of types to be parsed and set for this card
     * @throws NullPointerException if the input string is null
     */
    public List<String> parseTypeList( String types) {
        return Arrays.asList(types.split(" "));
    }
}