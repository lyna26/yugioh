package com.example.yugioh.models.player;

import com.example.yugioh.models.card.Card;
import com.example.yugioh.models.card.MonsterCard;
import com.example.yugioh.models.deck.DeckSet;
import com.example.yugioh.models.field.Field;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<DeckSet> decks;
    private String name;
    private DeckSet duelDeck;
    private transient Field field;
    private transient int lp;
    private transient boolean isMonsterNormalSummoned;

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
            System.out.println("drawing" + card.getName());
            card.setImage(new Image(card.getCardImage()));
            card.setFitWidth(150);
            card.setFitHeight(150);
            field.getMainDeckZone().removeCard(card);
            field.getHandZone().addCard(card);
    }

    /**
     * A selected card is moved from the hand to the graveyard
     * @param card : selected card
    */
    public void discard(Card card){
        field.getHandZone().removeCard(card);
        field.getGraveyardZone().addCard(card);
    }

    public void shuffleDeck(){
        duelDeck.getMainDeck().shuffle();
    }

    /**
     * Monster is moved from the hand to the field in
     */
    public void summonMonster(MonsterCard monsterCard){
        //TODO see who handles monster position and maybe add getter/setter to monster card monsterCard.setPosition(position);
        //TODO see who handles monster position and maybe add getter/setter to monster card  monsterCard.setFace(face);
        field.getHandZone().removeCard(monsterCard);
        field.getMonsterZone().addCard(monsterCard);
    }

    /**
     * Performs a normal summon.
     */
    public void normalSummon(MonsterCard monsterCard){
        if(monsterCard.getLevel() <= 4) {
            summonMonster(monsterCard);
        }
        else{
            tributeSummon(monsterCard);
        }

        isMonsterNormalSummoned = true;
    }

    /**
     * Monsters with level 4 or higher need to be tribute summoned.
     */
    public void tributeSummon(MonsterCard monster){
        //TODO get list of monster to discard
        summonMonster(monster);
    }

    /**
     * Switches monster from ATTACK to DEFENSE or from DEFENSE to ATTACK.
     */
    public void switchMonsterMode(MonsterCard monsterCard){}

    public void putMainDeck(){
        field.getMainDeckZone().setCards( FXCollections.observableList(duelDeck.getMainDeck().getCardList()));
    }
    public void putExtraDeck(){
        field.getExtraDeckZone().setCards(FXCollections.observableList(duelDeck.getExtraDeck().getCardList()));
    }
}