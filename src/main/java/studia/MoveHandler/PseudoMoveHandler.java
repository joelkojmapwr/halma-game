package studia.MoveHandler;

import studia.Board.Board;
import studia.Utils.Player;
import studia.Utils.Point;

/**
 * PseudoMoveHandler is a class that is used only to simulate moves on the board without any validation to test the board
 */
public class PseudoMoveHandler implements MoveHandler {
    private Board board;

    public PseudoMoveHandler(Board board) {
        this.board = board;
    }

    public Boolean newMove(int oldPos, int newPos, Player player) {
        Point oldPoint = board.validPointsMap.get(oldPos);
        Point newPoint = board.validPointsMap.get(newPos);
        board.move(oldPoint, newPoint);
        return true;
    }
    
}
