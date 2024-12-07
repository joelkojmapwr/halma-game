package studia.winChecker;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import studia.Board.Pawn;
import studia.Board.Player;
import studia.Utils.Point;
import studia.Board.Board;

public class StandardWinChecker implements WinChecker {

    private Board board;
    private int pawnsPerPlayer;
    private Queue<Point> pointQueue;
    private int countPawnsHome = 0;
    private List<Point> visitedPoints = new ArrayList<Point>();

    public StandardWinChecker(Board board, int pawnsPerPlayer) {
        this.board = board;
        this.pawnsPerPlayer = pawnsPerPlayer;
    }

    public Boolean checkWin(Player player) {
        pointQueue = new LinkedList<Point>();
        visitedPoints = new ArrayList<Point>();
        countPawnsHome = 0;
        return searchNeighbours(player, player.finishCorner);
    }

    public Boolean searchNeighbours(Player player, Point currentPoint) {
        visitedPoints.add(currentPoint);
        if (currentPoint.pawn != null) {
            if (currentPoint.pawn.color == player.color) {
                countPawnsHome++;
                if (countPawnsHome == pawnsPerPlayer) {
                    return true;
                }
                for (Point p : currentPoint.neighbours1){
                    // if point already has a pawn or already is in the queue then skip
                    if (pointQueue.contains(p) || visitedPoints.contains(p)){
                        continue;
                    }
                    else {
                        pointQueue.add(p);
                    }
                }
                return searchNeighbours(player, pointQueue.poll());
            }
            else {
                return false;
            }
        }
        return false;
    }
}
