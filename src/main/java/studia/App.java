package studia;

import studia.Board.BoardBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studia.Board.Board;
import studia.MoveHandler.StandardMoveHandler;
import studia.Utils.Pair;
import studia.Utils.Player;
import studia.Utils.Point;
import studia.winChecker.StandardWinChecker;
import studia.winChecker.WinChecker;

/**
 * Hello world!
 */
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
        // nice this is working;
        //board.points[12][12].printNeighbours();
        System.out.println(board.countPoints);

        Scanner scanner = new Scanner(System.in);

        StandardMoveHandler moveHandler = new StandardMoveHandler(board);

        WinChecker winChecker = new StandardWinChecker(board, 10);

        while(true){
            board.printBoard();
            int oldPoint = scanner.nextInt();
            int newPoint = scanner.nextInt();
            //moveHandler.newMove(oldPoint, newPoint);
            System.out.println(winChecker.checkWin(board.players.get(0)));

            board.players.get(0).finishCorner = board.players.get(0).startCorner;
            winChecker.checkWin(board.players.get(0));
        }
    }
}
