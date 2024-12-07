package studia;

import java.util.Scanner;

import studia.Board.BoardBuilder;
import studia.Board.Board;
import studia.MoveHandler.MoveHandler;
import studia.MoveHandler.StandardMoveHandler;
import studia.Utils.Point;
import studia.winChecker.StandardWinChecker;
import studia.winChecker.WinChecker;


public class App {
    public static void main(String[] args) {
        int players = 2;
        BoardBuilder boardBuilder = new BoardBuilder(4, players, 10);
        boardBuilder.build();
        Board board = boardBuilder.getBoard();
        board.printBoard();
        for (Point p : board.cornerPoints) {
            p.printNeighbours2();

        }

        Scanner scanner = new Scanner(System.in);

        MoveHandler moveHandler = new StandardMoveHandler(board);

        WinChecker winChecker = new StandardWinChecker(10);

        while(true){
            board.printBoard();
            int oldPoint = scanner.nextInt();
            int newPoint = scanner.nextInt();
            moveHandler.newMove(oldPoint, newPoint, board.players.get(0));
            
            winChecker.checkWin(board.players.get(0));
        }
    }
}
