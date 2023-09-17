package com.example.yugioh.models.field;
import com.example.yugioh.models.card.Card;


import com.example.yugioh.models.card.CardImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class ZoneImpl implements Zone {
    public ZoneImpl() {}

    public abstract void addCard(CardImpl card);

    public abstract void removeCard(CardImpl card);
}
