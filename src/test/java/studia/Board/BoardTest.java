package studia.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class BoardTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void pointsAmountOnBoard() {
        //for (int i=4; i<10; i++){
            int trianglesize = 4;
            List<Player> players = new ArrayList<Player>();
            Player player1 = new Player(new Pair(12, 0), 32);
            players.add(player1);
            Player player2 = new Player(new Pair(12, 16), 14);
            players.add(player2);
            BoardBuilder boardBuilder = new BoardBuilder2Player(trianglesize, players);
            Board board = boardBuilder.board;
            int pointsAmountOnBoard = trianglesize*(trianglesize+1)/2 * 12 + 1; // defaultly (for trianglesize = 4) 121
            // The amount of points equals (trianglesize*(trianglesize+1)/2 * 12 + 1
            assertEquals(pointsAmountOnBoard, board.countPoints);
        //}
        
    }
}
