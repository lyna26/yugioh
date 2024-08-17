package com.example.yugioh.models.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
public class TrapCard extends CardImpl {
    public TrapCard(ResultSet card) throws SQLException {
        super(card);
    }
}
