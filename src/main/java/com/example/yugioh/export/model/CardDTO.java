package com.example.yugioh.export.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Entity;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@Table(schema = "dbo", name="card")
public class CardDTO {
    //BASIS
    @Id
    private final int id;
    private final String name;
    @Column(name = "[desc]")
    private final String description;
    private String image_url;
    private String image_url_small;
    private final String race;
    private final String banlist_info;
    private final String frameType;
    private final int level;
    private final String type;

    //LINK
    private final int linkval;
    private final String linkmarkers;

    //MONSTER
    private final int atk;
    private final int def;
    private final String attribute;

    //SCALE
    private final int scale;
}
