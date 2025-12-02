package com.example.yugioh.auth.entity.deck;


import com.example.yugioh.auth.entity.Card;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
@Table(name = "Deck", schema = "dbo")
@DiscriminatorColumn(
        name = "type",
        discriminatorType = STRING
)
@NoArgsConstructor
@Getter
public abstract class Deck {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;

    @ManyToMany
    @JoinTable(
            name = "DeckCard",
            joinColumns = @JoinColumn(name = "deckId"),
            inverseJoinColumns = @JoinColumn(name = "cardId")
    )
    private List<Card> cardList = new ArrayList<>();

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "deck_collection_id", nullable = false)
    private DeckCollection deckCollection;
}
