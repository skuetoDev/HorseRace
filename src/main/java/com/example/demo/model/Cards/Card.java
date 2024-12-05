package com.example.demo.model.Cards;

public abstract class Card {
    protected CardSuit suit;
    protected float value;

    protected String toString(String numberOrFace) {
       return String.format("%7s of %6s, value %.1f",numberOrFace,suit, value);
    }

    public abstract String getDescription();

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public float getValue() {
        return value;
    }
}
