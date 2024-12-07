package studia.MoveHandler;

import studia.Board.Board;
import studia.Utils.Point;

public class PseudoMoveHandler implements MoveHandler {
    private Board board;

    public PseudoMoveHandler(Board board) {
        this.board = board;
    }

    public void newMove(int oldPos, int newPos) {
        Point oldPoint = board.validPointsMap.get(oldPos);
        Point newPoint = board.validPointsMap.get(newPos);
        board.move(oldPoint, newPoint);
    }
    
}
