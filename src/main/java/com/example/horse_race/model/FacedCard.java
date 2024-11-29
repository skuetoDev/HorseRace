package com.example.horse_race.model;

public class FacedCard extends Card{

    private final CardFace face;

    public FacedCard(CardFace cardFace, CardSuit pal) {
        super.value = 0.5f;
        super.suit = pal;
        this.face = cardFace;
    }


    /**
     * Method to get description from facedCard
     * @return a String whose content card information, implements from Card class
     */
    @Override
    public String getDescription() {

        return face + " of " + suit ;

    }
}
