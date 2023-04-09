package com.example.yugioh.models.field;


import com.example.yugioh.models.card.Card;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public abstract class Zone implements IZone {
    private ObservableList<Card> cards;

    public Zone() {
        cards = FXCollections.observableArrayList();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }
}
