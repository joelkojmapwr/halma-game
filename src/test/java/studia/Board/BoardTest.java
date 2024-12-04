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
            int playerNumber = 3;
            BoardBuilder boardBuilder = new BoardBuilder2Player(trianglesize, playerNumber);
            Board board = boardBuilder.board;
            int pointsAmountOnBoard = trianglesize*(trianglesize+1)/2 * 12 + 1; // defaultly (for trianglesize = 4) 121
            // The amount of points equals (trianglesize*(trianglesize+1)/2 * 12 + 1
            assertEquals(pointsAmountOnBoard, board.countPoints);
        //}
    }

    @Test
    public void testBuildingBoardForEveryPlayerNumber() {
        for (int i=2; i<=6; i++){
            if (i==5){
                continue;
            }
            int trianglesize = 4;
            BoardBuilder boardBuilder = new BoardBuilder2Player(trianglesize, i);
            Board board = boardBuilder.board;
            //int pointsAmountOnBoard = trianglesize*(trianglesize+1)/2 * 12 + 1;
            board.printBoard();
        }
    }
}
