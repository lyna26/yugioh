package com.example.yugioh.auth.entity.deck;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;

/**
 * A class representing a deck in the Yu-Gi-Oh trading card game.
 */
@Entity
@Slf4j
@DiscriminatorValue("MAIN")
public class DeckMain extends Deck { }
