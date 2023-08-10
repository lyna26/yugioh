package com.example.yugioh.models.player;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountDown {
    IntegerProperty timeToGive = new SimpleIntegerProperty(200);
    IntegerProperty remainingTime;

    public CountDown() {
        this.remainingTime = new SimpleIntegerProperty(200);
    }
    public IntegerProperty getTimeToGive() {
        return timeToGive;
    }
    public void decrementTime() {
        remainingTime.set(remainingTime.get() - 1);
    }
}