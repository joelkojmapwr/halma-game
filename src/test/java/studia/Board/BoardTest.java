package studia.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import studia.Utils.Pair;

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
            BoardBuilder boardBuilder = new BoardBuilder(trianglesize, playerNumber, 10);
            boardBuilder.build();
            Board board = boardBuilder.getBoard();
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
            BoardBuilder boardBuilder = new BoardBuilder(trianglesize, i, 10);
            boardBuilder.build();
            Board board = boardBuilder.getBoard();
            //int pointsAmountOnBoard = trianglesize*(trianglesize+1)/2 * 12 + 1;
            board.printBoard();
        }
    }

    @Test
    public void testMovingPawns(){
        Board board = defaultBoard();
        //board.printBoard();
        board.move(new Pair(13, 3), new Pair(14, 4));
        assertTrue(board.points[14][4].pawn != null);
        board.printBoard();
    }

    public Board defaultBoard() {
        int trianglesize = 4;
        int playerNumber = 6;
        BoardBuilder boardBuilder = new BoardBuilder(trianglesize, playerNumber, 10);
        boardBuilder.build();
        Board board = boardBuilder.getBoard();
        return board;
    }
}
