package studia.Board;

import studia.Utils.Pair;
import java.util.List;
import java.util.ArrayList;

public class MoveHandler {
    public Board board;

    private List<Point> visitedPoints = new ArrayList<Point>();

    public MoveHandler(Board board){
        this.board = board;
    }

    /**
     * @TODO Add exceptions for invalid moves
     * @param oldPos
     * @param newPos
     */
    public void newMove(int oldPos, int newPos){

        if (oldPos > board.length*board.height || newPos > board.length*board.height){
            System.out.println("Invalid Move");
            return;
        }

        int x = oldPos % board.length;
        int y = oldPos / board.length;
        Point oldPoint = board.points[x][y];
        x = newPos % board.length;
        y = newPos / board.length;
        Point newPoint = board.points[x][y];
        if (isValidMove(oldPoint, newPoint)){
            board.move(oldPoint.pos, newPoint.pos);
        }
        else {
            System.out.println("Invalid Move");
        }
    }

    public Boolean isValidMove(Point oldPoint, Point newPoint) {

        if (isMoveToNeighbour1(oldPoint, newPoint) == true) {
            return true;
        }
        if (isMoveJump(oldPoint, newPoint) == true) {
            return true;
        }
        return false;
    }

    public Boolean isMoveToNeighbour1(Point oldPoint, Point newPoint) {
        return oldPoint.neighbours1.contains(newPoint);
    }

    public Boolean isMoveJump(Point oldPoint, Point newPoint) {
        visitedPoints.clear();
        for (Point neighbour1 : oldPoint.neighbours1) {
            if (neighbour1.pawn != null) {
                // there is a pawn so jump is possible
                Point neighbour2 = oldPoint.getNeighbour2FromNeighbour1(neighbour1);
                if (neighbour2 == null) {
                    // this neighbour does not exist
                    continue;
                }
                if (searchJumps(neighbour2, newPoint) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean searchJumps(Point currentPoint, Point destination) {
        visitedPoints.add(currentPoint);
        if (currentPoint.pawn != null) {
            // this place is not free so jump is not possible into this place
            return false;
        }

        if (currentPoint == destination) {
            // we have reached the destination
            return true;
        }

        for (Point neighbour1 : currentPoint.neighbours1) {
            if (neighbour1.pawn != null) {
                // there is a pawn so jump is possible
                Point neighbour2 = currentPoint.getNeighbour2FromNeighbour1(neighbour1);
                if (neighbour2 == null) {
                    // this neighbour does not exist
                    continue;
                }
                if (visitedPoints.contains(neighbour2) == true) {
                    // we have already visited this point
                    continue;
                } 
                else {
                    if (searchJumps(neighbour2, destination) == true) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
