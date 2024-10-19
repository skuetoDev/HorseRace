package src.logic;
import src.model.Card;
import src.model.CardsDeck;

import static src.helper.Prints.*;



public class GameLogic {

    //fields
    private int row = 4;
    private int column = 10;
    protected int[][] horsesPosition;
    private int round = 0 ;
    private CardsDeck cardsDeck ;
    private Card card ;
    boolean winner = false;



    //constructor
    public GameLogic() {
        horsesPosition = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 0) {
                    horsesPosition[i][j] = 1;
                }
            }
        }
        printPositions(horsesPosition);
        cardsDeck = new CardsDeck(); //create deck
    }

    public void horseMovemnt (){
        do {
            card = cardsDeck.getCardFromDeck();
            round++;
            if (round % 5 == 0 ) {

                printTextNumber("Round : ", round);
                bakckward(card);
            } else {
                printTextNumber("Round : ", round);
                fordward(card);
            }

        }while(!winner);

    }


    /**
     * Method to add one position in horsesPosition array, no matter what suit it was
     * @param card from the deck to get the suit
     */
    private void fordward (Card card){
        String suit;
        printCard(card);
        suit = String.valueOf(card.getSuit());
        switch (suit){
            case "SWORDS":
                addPosition(0);
                break;
            case "GOLD":
                addPosition(1);
                break;
            case "CUPS":
                addPosition(2);
                break;
            case "CLUBS":
                addPosition(3);
                break;
            default:
                printTextNumber("ERROR: ",1);
                break;
        }
        printPositions(horsesPosition);
        if (winner){
            printText("HA GANADO EL CABALLO DE "+suit);
        }
    }

    private void bakckward (Card card){
        String suit;
        printCard(card);
        suit = String.valueOf(card.getSuit());
        switch (suit){
            case "SWORDS":
                subPosition(0);
                break;
            case "GOLD":
                subPosition(1);
                break;
            case "CUPS":
                subPosition(2);
                break;
            case "CLUBS":
                subPosition(3);
                break;
            default:
                printTextNumber("ERROR: ",2);
                break;
        }
        printPositions(horsesPosition);
    }


    private void addPosition(int road){
        if (horsesPosition[road][9] == 1) {
            winner = true;
            return;
        }
        for(int j = 0; j < horsesPosition[road].length ; j++) {
            if (horsesPosition[road][j] == 1) {
                horsesPosition[road][j] = 0;
                horsesPosition[road][j + 1] = 1;
                break;
            }
        }
    }

    private void subPosition(int road){
        if (horsesPosition[road][0] == 1) {
            return;
        }
        for(int j = 0; j < horsesPosition[road].length ; j++) {
            if (horsesPosition[road][j] == 1) {
                horsesPosition[road][j] = 0;
                horsesPosition[road][j - 1] = 1;
                break;
            }
        }
    }
    // a eliminar
    public int[][] getHorsesPosition() {
        return horsesPosition;
    }
}

