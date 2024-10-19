package src.model;

public class FacedCard extends Card{

    private CardFace face;

    public FacedCard(CardFace cardFace, CardSuit pal) {
        super.value = 0.5f;
        super.suit = pal;
        this.face = cardFace;
    }

    /**
     *  ToString method to display card data
     * @return --> Num, Pal i valor
     */
    @Override
    public String toString() {
        return super.toString(this.face.toString());
    }

    @Override
    public String getDescription() {
        return this.face.toString()+" of "+suit;
    }
}
