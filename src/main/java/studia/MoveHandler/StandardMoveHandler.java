package studia.MoveHandler;

import studia.Board.Board;
import studia.Utils.Player;
import studia.Utils.Point;
import studia.Utils.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Handles and validates moves on the board
 */
public class StandardMoveHandler implements MoveHandler {

    private Board board;
    private List<Point> visitedPoints = new ArrayList<Point>();

    public StandardMoveHandler(Board board){
        this.board = board;
    }

    /**
     * @param oldPos - old position of the pawn
     * @param newPos - new position of the pawn
     * @param player - player that is making the move
     */
    public Boolean newMove(int oldPos, int newPos, Player player){
        Point oldPoint;
        Point newPoint;
        
        try {
            oldPoint = board.validPointsMap.get(oldPos);
            newPoint = board.validPointsMap.get(newPos);
        }
        catch (Exception e) {
            //System.out.println("Invalid index");
            return false;
        }
        
        if(oldPoint == null || newPoint == null) return false; //Map returns null when no mapping

        if (isValidMove(oldPoint, newPoint, player) == true) {
            board.move(oldPoint, newPoint);
            return true;
        }
        return false;
    }
    /**
     * Checks if the move is valid
     * @param oldPoint
     * @param newPoint
     * @return
     */
    private Boolean isValidMove(Point oldPoint, Point newPoint, Player player){
        if (oldPoint.pawn == null) {
            // empty field invalid move
            return false;
        }
        if (oldPoint.pawn.color != player.getColor()) {
            // not your pawn
            return false;
        }
        if(oldPoint == newPoint) {
					// stand
					return true;
				}
        if (isMoveLeavingHome(oldPoint, newPoint, player) == true) {
            return false;
        }
        if (newPoint.pawn != null) {
            // there is a pawn on the new field, so you can can not move there
            return false;
        }
        if (isMoveToNeighbour1(oldPoint, newPoint) == true) {
            return true;
        }
        if (isMoveJump(oldPoint, newPoint) == true) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the move is to the neighbour1
     * @param oldPoint
     * @param newPoint
     * @return
     */
    private Boolean isMoveToNeighbour1(Point oldPoint, Point newPoint) {
        return oldPoint.neighbours1.contains(newPoint);
    }
    /**
     * Checks if the move is a jump
     * @param oldPoint
     * @param newPoint
     * @return
     */
    private Boolean isMoveJump(Point oldPoint, Point newPoint) {
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

    /**
     * Searches for possible jumps
     * @param currentPoint
     * @param destination
     * @return
     */
    private Boolean searchJumps(Point currentPoint, Point destination) {
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
    /**
     * Checks if the move is leaving home
     * @param oldPoint
     * @param newPoint
     * @param player
     * @return
     */
    private Boolean isMoveLeavingHome(Point oldPoint, Point newPoint, Player player) {
        if (player.finishPoints.contains(oldPoint) == true && player.finishPoints.contains(newPoint) == false) {
            //System.out.println("You can not leave home");
            return true;
        }
        return false;
    }
    
    private List<Point> _getAvailableMoves(Player plr, Point from, Point cur, Set<Point> visited, List<Point> ret) {
			visited.add(cur);
			for(Point n: cur.neighbours1) {
				if(visited.contains(n)) continue;
				if(isValidMove(from, n, plr))
					ret.add(n);
				ret = _getAvailableMoves(plr, from, n, visited, ret);
			}
			return ret;
		}
		/**
         * Returns a list of all available moves for the player
         * @param plr
         * @param from
         * @return
         */
		public List<Point> getAvailableMoves(Player plr, Point from) {
			List<Point> l = new ArrayList<Point>();
			Set<Point> v = new HashSet<Point>();
			return _getAvailableMoves(plr, from, from, v, l);
		}
		

}
