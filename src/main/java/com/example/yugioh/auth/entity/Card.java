package com.example.yugioh.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "Card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    // Commun
    @Id
    int id;
    String name;
    String description;
    String imageUrl;
    String imageUrlSmall;
    String race;
    String banlistInfo;
    String frameType;
    int level;
    String type;
    boolean hasEffect;

    //LINK
    int linkVal;
    String linkMarkers;

    //MONSTER
    int atk;
    int def;
    String attribute;

    //SCALE
    int scale;
}
