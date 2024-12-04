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
        List<Player> players = new ArrayList<Player>();
            Player player1 = new Player(new Pair(12, 0), 32);
            players.add(player1);
            Player player2 = new Player(new Pair(12, 16), 14);
            players.add(player2);
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
