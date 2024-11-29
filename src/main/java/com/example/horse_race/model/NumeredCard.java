package com.example.horse_race.model;

public class NumeredCard extends Card{

    private int num;
    /**
     * Constructor per crear les Cartes amb el seu cardSuit i el seu valor cridant un metode helper
     *
     * @param num --> Numero de carta
     * @param cardSuit --> Pal de la carta
     */
    public NumeredCard(int num, CardSuit cardSuit) {
        this.num = num;
        super.suit = cardSuit;
        super.value = num;
    }

    /**
     *  ToString method to display card data
     * @return --> Num, Pal i valor
     */
    @Override
    public String toString() {
        return super.toString(String.valueOf(this.num));
    }

    /**
     * Method to get description from NumeredCard
     * @return a String whose content card information, implements from Card class
     */
    @Override
    public String getDescription() {

        return num + " of " + suit ;
    }

}
