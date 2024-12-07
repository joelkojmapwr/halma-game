package studia.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import studia.MoveHandler.MoveHandler;
import studia.MoveHandler.PseudoMoveHandler;
import studia.Utils.Player;
import studia.winChecker.StandardWinChecker;
import studia.winChecker.WinChecker;

/**
 * Unit test for simple App.
 */
public class BoardTest {

    @Test
    public void pointsAmountOnBoard() {
            int trianglesize = 4;
            int playerNumber = 3;
            BoardBuilder boardBuilder = new BoardBuilder(trianglesize, playerNumber, 10);
            boardBuilder.build();
            Board board = boardBuilder.getBoard();
            int pointsAmountOnBoard = trianglesize*(trianglesize+1)/2 * 12 + 1; // defaultly (for trianglesize = 4) 121
            // The amount of points equals (trianglesize*(trianglesize+1)/2 * 12 + 1
            assertEquals(pointsAmountOnBoard, board.validPointsNumber);
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
        Board board = defaultBoard(6);
        //board.printBoard();
        board.move(board.points[13][3], board.points[14][4]);
        assertTrue(board.points[14][4].pawn != null);
        board.printBoard();
    }

    @Test
    public void testStartAndFinishCorners(){
        Board board = defaultBoard(6);
        //board.printBoard();
        for (Player player : board.players) {
            assertEquals(player.startCorner.pos.x + player.finishCorner.pos.x, board.length-1);
            assertEquals(player.startCorner.pos.y + player.finishCorner.pos.y, board.height-1);
        }
    }

    @Test
    public void winChecker() {
        Board board = defaultBoard(2);
        MoveHandler moveHandler = new PseudoMoveHandler(board);
        WinChecker winChecker = new StandardWinChecker(10);
        assertFalse(winChecker.checkWin(board.players.get(0)));

        // simulate that his finish corner is whhere he starts so now the winchecker should return that this player won
        board.players.get(0).finishCorner = board.players.get(0).startCorner;
        assertTrue(winChecker.checkWin(board.players.get(0)));
        // now we moved one pawn from the finish corner so the player should not win
        moveHandler.newMove(6, 14, board.players.get(0));
        assertFalse(winChecker.checkWin(board.players.get(0)));

        // check if it recognizes that pawn doesn't  belong to player that should win
        moveHandler.newMove(111, 6, board.players.get(1));
        assertFalse(winChecker.checkWin(board.players.get(0)));
    }


    public Board defaultBoard(int playerNumber) {
        int trianglesize = 4;
        BoardBuilder boardBuilder = new BoardBuilder(trianglesize, playerNumber, 10);
        boardBuilder.build();
        Board board = boardBuilder.getBoard();
        return board;
    }
}
