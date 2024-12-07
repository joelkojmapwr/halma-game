package studia;

import java.util.Scanner;

import studia.Board.BoardBuilder;
import studia.Board.Board;
import studia.MoveHandler.MoveHandler;
import studia.MoveHandler.StandardMoveHandler;
import studia.Utils.Player;
import studia.Utils.Point;
import studia.winChecker.StandardWinChecker;
import studia.winChecker.WinChecker;


public class App {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        players[0] = new Player(1);
        players[1] = new Player(2);
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
            int player = scanner.nextInt();
            moveHandler.newMove(oldPoint, newPoint, board.players[player]);
            
            winChecker.checkWin(board.players[0]);
        }
    }
}
