package studia;

import studia.Board.BoardBuilder;

import java.util.ArrayList;
import java.util.List;

import studia.Board.Board;
import studia.Board.BoardBuilder2Player;
import studia.Board.Pair;
import studia.Board.Player;
import studia.Board.Point;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        int players = 3;
        BoardBuilder boardBuilder = new BoardBuilder2Player(4, players);
        Board board = boardBuilder.board;
        board.printBoard();
        for (Point p : board.cornerPoints) {
            p.printNeighbours();

        }
        // nice this is working;
        //board.points[12][12].printNeighbours();
        System.out.println(board.countPoints);


    }
}
