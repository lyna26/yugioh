package com.example.yugioh.models.player;

import com.example.yugioh.enums.Face;
import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.MonsterCard;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.models.field.Field;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
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
    private DeckSet duelDeck;//maybe use only int (index) instead.
    private transient Field field;
    private transient LpProgressBar lp;
    private transient boolean isMonsterNormalSummoned;
    private String sleeve = "C://Users//Lola//IdeaProjects//demo//src//main//resources//com//example//yugioh//images//Yugioh_Card_Back.jpg";

    public Player(String name){
        this.name = name;
        this.decks = new ArrayList<>();
        isMonsterNormalSummoned = false;
    }

    /**
     * First card of the deck is moved to the hand
     */
    public void draw(){
            Card card = field.getMainDeckZone().getCards().get(0);
            log.info("The player {} draw {}", this.name, card.getName());
            card.setImage(new Image(card.getCardImage()));
            card.setFitWidth(150);
            card.setFitHeight(150);
            field.getMainDeckZone().removeCard(card);
            field.getHandZone().addCard(field.getHandZone().getCards().size(), card);
    }

    /**
     * A selected card is moved from the hand to the graveyard
     * @param card : selected card
    */
    public void discard(Card card){
        field.getHandZone().removeCard(card);
        //field.getGraveyardZone().addCard(card);
    }

    public void shuffleDeck(){
        duelDeck.getMainDeck().shuffle();
    }

    /**
     * Summon a monster.
     * @param monsterCard monster to summon
     */
    public void summonMonster(int index, MonsterCard monsterCard){
        field.getHandZone().removeCard(monsterCard);
        field.getMonsterZone().addCard(index, monsterCard);
    }


    public void putCardOnTheSpellTrapZone(int index, Card card) {
        field.getHandZone().removeCard(card);
        field.getSpellTrapZone().addCard(index, card);
    }

    public void putCardOnMonsterZone(int index, MonsterCard card) {
        field.getHandZone().removeCard(card);
        field.getMonsterZone().addCard(index, card);
    }

    /**
     * Switches monster from ATTACK to DEFENSE or from DEFENSE to ATTACK.
     */
    public void switchMonsterMode(MonsterCard monsterCard){
    }

    public void putMainDeck(){
        field.getMainDeckZone().setCards(FXCollections.observableList(duelDeck.getMainDeck().getCardList()));
    }
    public void putExtraDeck(){
        field.getExtraDeckZone().setCards(FXCollections.observableList(duelDeck.getExtraDeck().getCardList()));
    }
}