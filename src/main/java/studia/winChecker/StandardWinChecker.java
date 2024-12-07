package studia.winChecker;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import studia.Utils.Player;
import studia.Utils.Point;

public class StandardWinChecker implements WinChecker {

    private int pawnsPerPlayer;
    private Queue<Point> pointQueue;
    private int countPawnsHome = 0;
    private List<Point> visitedPoints = new ArrayList<Point>();

    public StandardWinChecker(int pawnsPerPlayer) {
        this.pawnsPerPlayer = pawnsPerPlayer;
    }

    public Boolean checkWin(Player player) {
        pointQueue = new LinkedList<Point>();
        visitedPoints = new ArrayList<Point>();
        countPawnsHome = 0;
        return searchNeighbours(player, player.finishCorner);
    }

    private Boolean searchNeighbours(Player player, Point currentPoint) {
        visitedPoints.add(currentPoint);
        if (currentPoint.pawn != null) {
            if (currentPoint.pawn.color == player.getColor()) {
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
