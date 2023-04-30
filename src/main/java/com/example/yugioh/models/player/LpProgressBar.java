package com.example.yugioh.models.player;
;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LpProgressBar {
    private IntegerProperty maxLp;
    private IntegerProperty currentLp;

    public LpProgressBar(int maxLp)
    {
        this.maxLp =  new SimpleIntegerProperty(maxLp);
        this.currentLp  = new SimpleIntegerProperty(maxLp);
    }
}
