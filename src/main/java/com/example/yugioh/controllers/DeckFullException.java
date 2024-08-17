package com.example.yugioh.controllers;

public class DeckFullException extends Exception{
    public DeckFullException() {
        super("Deck is full, cannot add more cards.");
    }

    public DeckFullException(String message) {
        super(message);
    }
}
