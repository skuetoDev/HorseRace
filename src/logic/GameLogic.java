package src.model;
import java.util.Arrays;

public class GameLogic {

    //fields
    private int row = 9;
    private int column = 4;
    private int[][] horsesPostion;

    //constructor
    public GameLogic() {

        horsesPostion = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0) {
                    horsesPostion[i][j] = 1;
                }else {
                    horsesPostion[i][j] = 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(horsesPostion);

    }
}

