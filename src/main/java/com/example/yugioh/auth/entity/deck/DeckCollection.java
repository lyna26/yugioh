package com.example.yugioh.auth.entity.deck;

import com.example.yugioh.auth.entity.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.AUTO;

/**
 * This class represents a complete deck ( main, side & extra) in the game of Yu-Gi-Oh!.
 */
@Entity
@Table(name = "DeckCollection")
@NoArgsConstructor
@Getter
public class DeckCollection {
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;

    @Setter
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @OneToMany(mappedBy = "deckCollection", cascade = ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Deck> decks = new ArrayList<>();

    public DeckCollection(String name, Player player) {
        this.name = name;
        this.player = player;
    }

}