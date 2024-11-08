package com.example.yugioh.models.player;

import com.example.yugioh.models.deck.DeckSet;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Slf4j
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<DeckSet> decks;
    private String name;
    private DeckSet duelDeck;
    private DeckSet modifiedDeck ;
    private transient boolean isMonsterNormalSummoned;
    private String sleeve = "C://Users//Lola//IdeaProjects//demo//src//main//resources//com//example//yugioh//images//Yugioh_Card_Back.jpg";

    public Player(String name){
        this.name = name;
        this.decks = new ArrayList<>();
        isMonsterNormalSummoned = false;
    }
}