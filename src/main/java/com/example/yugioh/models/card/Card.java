package com.example.yugioh.models.card;

import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * This abstract class represents a Yu-Gi-Oh card.
 * It contains common attributes and methods that are shared by all types of cards.
 */
@Getter
@ToString
public abstract class Card extends ImageView implements Serializable {
    @Serial
    private final static long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(Card.class.getName());
    private final int cardId;
    private final String name;
    private final String description;
    private final String cardImage;
    private final List<String> types;

    /**
     * Constructor used to generate a card from data exported from a database.
     *
     * @param card ResultSet containing card information.
     */
    public Card(ResultSet card) throws SQLException {
        this.cardId = card.getInt("id");
        this.name = card.getString("name");
        this.description = card.getString("desc");
        this.cardImage = card.getString("image_url");
        this.types = parseTypeList(card.getString("type"));
    }

    /**
     * Parses a string of types and sets the types of this card accordingly.
     * The types are expected to be separated by a space character.
     *
     * @param types a string of types to be parsed and set for this card
     * @throws NullPointerException if the input string is null
     */
    public List<String> parseTypeList( String types) {
        String[] separator = {" "};
        String[] typeList = types.split(Arrays.toString(separator));
        return Arrays.asList(typeList);
    }
}