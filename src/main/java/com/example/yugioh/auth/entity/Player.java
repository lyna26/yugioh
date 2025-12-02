package com.example.yugioh.auth.entity;

import com.example.yugioh.auth.entity.deck.DeckCollection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.AUTO;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "Player", schema = "dbo")
public class Player{
    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "player")
    private List<DeckCollection> decks = new ArrayList<>();


    public Player(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }
}