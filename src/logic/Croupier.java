package src.logic;
import src.players.*;
import java.util.ArrayList;
import static src.helper.Prints.*;
import static src.helper.Reads.*;



public class Croupier {


    //fields
    private int balance;
    private ArrayList<Player> players;


    //constructor
    public Croupier (){
    players = new ArrayList<>();
    configureUsers();
    printPlayers(players);

    }

    //methods

    public void configureUsers(){
        printWelcome();
        printText("How many users are Human? (1-4)");
        int humanPlayers = getInt();
        if (humanPlayers >= 1 && humanPlayers <= 4){
            createPlayers(humanPlayers);

        }else{
            printOutOfRange();
        }
    }

    private void createPlayers (int humanPlayer){

        //create human players with his bet
        for( int i = 0 ; i < humanPlayer ; i++){
            printTextNumber("Name of Player ",(i+1));
            String namePlayer = getText();
            printText(namePlayer+"´s bet");
            int bet = getInt();
            printHorseSuit();
            int suit = getInt();
            Human human = new Human(namePlayer,bet,suit);
            players.add(human);

        }
        // create bot playes with his bet
        for( int i = humanPlayer ; i < 4 ; i++){
            boolean exit = false;
            Bot playerBot = new Bot();
            do{
                if (players.size() <= humanPlayer){
                    exit =true;
                }else {
                    if (players.get(i-1).getName().equalsIgnoreCase(playerBot.getName()) || players.get(i-1).getHorseSuit().equalsIgnoreCase(playerBot.getHorseSuit())) {
                    } else {
                        exit = true;
                    }
                }
            }while(!exit);

            players.add(playerBot);
        }
    }


}
