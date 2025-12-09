package com.example.yugioh.auth.dto.card;

import com.example.yugioh.auth.entity.Card;
import com.example.yugioh.auth.enums.Limit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public abstract class CardImplDTO implements CardDTO {
    private int id;

    private String name;

    private String description;

    private String imageUrl;

    private String imageUrlSmall;

    private String race;

    private int limit;

    private int level;

    public CardImplDTO(final Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.imageUrl = card.getImageUrl();
        this.description = card.getDescription();
        this.race = card.getRace();
        this.limit = Limit.valueOf(card.getBanlistInfo()).getNbCopies();
        this.imageUrlSmall = card.getImageUrlSmall();
    }
}