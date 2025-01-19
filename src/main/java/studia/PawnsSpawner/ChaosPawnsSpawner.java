package studia.PawnsSpawner;

import java.util.Queue;
import java.util.Random;

import studia.Utils.Player;
import studia.Utils.Point;

import studia.Board.Board;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ChaosPawnsSpawner implements PawnsSpawner {

    private Queue<Point> pointQueue = new LinkedList<Point>();
    private Set<Point> visited = new HashSet<Point>();
    private List<Point> allowedPoints = new ArrayList<Point>();
    private Random rnd;
    
    private int maxPawns;
    
    public ChaosPawnsSpawner(Board b, int maxPawns, int seed){
        this.maxPawns = maxPawns;
        rnd = new Random((long) seed);
        for (Map.Entry<Integer, Point> entry : b.validPointsMap.entrySet()) {
            allowedPoints.add(entry.getValue());
        }
        
        try {
					for(Point p: b.cornerPoints) {
						pointQueue = new LinkedList<Point>();
						visited = new HashSet<Point>();
						visited.add(p);
						removeHome(p, 0);
					}
				} catch (Exception e) { e.printStackTrace(); }
    }

    public void spawn(Player[] players) {
        for (Player player : players) {
            this.spawnPawns(player);
        }
    }
		
		private Point randomPoint() {
			int i = rnd.nextInt(allowedPoints.size());
			Point p = allowedPoints.get(i);
			allowedPoints.remove(p);
			return p;
		}
		
		private void spawnPawns(Player player) {
			for(int i=0;i<maxPawns;i++) {
				Point p = randomPoint();
				p.pawn = player.spawnNewPawn();
			}
		}
    

    private void removeHome(Point startPoint, int npoints) throws Exception {
        if (npoints > maxPawns) throw new Exception("Too many Pawns");
        if (npoints == maxPawns) return;

				allowedPoints.remove(startPoint);
        npoints++;
        
				if (npoints < maxPawns) {
					for (Point p : startPoint.neighbours1){
						if (visited.contains(p)) continue;
						pointQueue.add(p);
						visited.add(p);
					}
				}
				removeHome(pointQueue.poll(), npoints);
    }
}
