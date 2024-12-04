package studia.Board;

import java.util.Queue;
import java.util.LinkedList;

public class PawnsSpawner {

    public Queue<Point> pointQueue = new LinkedList<Point>();
    private int maxPawns;

    public PawnsSpawner(int maxPawns){
        this.maxPawns = maxPawns;
    }

    public void spawn(Player player, Point startPoint) throws Exception{
        // clean the list
        this.pointQueue = new LinkedList<Point>();
        spawnPawns(player, startPoint);
    }
    

    private void spawnPawns(Player player, Point startPoint) throws Exception{
        // starting from corner point
        if (player.countPawns > maxPawns){
            throw new Exception("Too many Pawns spawned");
        }
        else if (startPoint.pawn != null || player.countPawns == maxPawns){
            return;
        }
        else {
            startPoint.pawn = player.spawnNewPawn();
            
            if (player.countPawns < maxPawns){
                // add neigbours to queue
                for (Point p : startPoint.neighbours1){
                    // if point already has a pawn or already is in the queue then skip
                    if (pointQueue.contains(p) == true){
                        continue;
                    }
                    if (p.pawn == null){
                        pointQueue.add(p);
                    }
                }
                // now spawn for next point in queue
                spawnPawns(player, pointQueue.poll());
            }
        }
    }
}
