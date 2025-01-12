package studia.Board;

import studia.Utils.FXPoint;
import studia.Utils.Pair;
import studia.Utils.Player;

public class FXBoardBuilder extends BoardBuilder {

    public FXBoardBuilder(int triangleSize, Player[] players, int pawnsPerPlayer) {
        super(triangleSize, players, pawnsPerPlayer);
				this.board = new FXBoard();
        initBoard();
    }

    /**
     * Initializes Points but with {@link studia.Utils.FXPoint}
     */
    @Override
    protected void initPoints() {
        // punkt 0,0 jest w lewym dolnym rogu
        // initialize 1 triangle - 
        int newX, newY;
        for (int i = 0; i < board.height - board.triangleSize; i++) {
            for (int j = i%2; j <= i; j+=2) {
                // w parzystym wierszu pola są na parzystych pozycjach
                // w nieparzystym wierszu pola są na nieparzystych pozycjach
                newX = board.length/2 + j;
                newY = i;
                board.setPoint(new FXPoint(new Pair(newX, newY)), newX, newY);
                newX = board.length/2 - j;
                newY = i;
                board.setPoint(new FXPoint(new Pair(newX, newY)), newX, newY);
            }
        }
        // initialize 2 triangle
        for (int i = board.height - 1; i>=board.triangleSize; i--) {
            // zaczynamy od tego na środku
            for (int j = i%2; j <= (board.height -1 -i); j+=2) {
                // w parzystym wierszu pola są na parzystych pozycjach
                // w nieparzystym wierszu pola są na nieparzystych pozycjach
                newX = board.length/2 + j;
                newY = i;
                board.setPoint(new FXPoint(new Pair(newX, newY)), newX, newY);
                newX = board.length/2 - j;
                newY = i;
                board.setPoint(new FXPoint(new Pair(newX, newY)), newX, newY);
            }
        }
    }

    
}
