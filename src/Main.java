package src;


import src.model.Card;
import src.model.CardsDeck;

public class Main {
        public static void main(String[] args) {
            CardsDeck cardsDeck = new CardsDeck();
            Card card = cardsDeck.getCardFromDeck();
            System.out.println(card.getDescription());
        }

}