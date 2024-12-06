package studia;

import studia.Board.BoardBuilder;
import studia.Board.MoveHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studia.Board.Board;
import studia.Board.Player;
import studia.Board.Point;
import studia.Utils.Pair;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int players = 6;
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

        MoveHandler moveHandler = new MoveHandler(board);

        while(true){
            board.printBoard();
            int oldPoint = scanner.nextInt();
            int newPoint = scanner.nextInt();
            moveHandler.newMove(oldPoint, newPoint);
            /*
            int x = input % board.length;
            int y = input / board.length;
            Pair oldPoint = new Pair(x, y);
            input = scanner.nextInt();
            x = input % board.length;
            y = input / board.length;
            Pair newPoint = new Pair(x, y);
            board.move(oldPoint, newPoint);
            */
        }
    }
}
