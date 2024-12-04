package studia.Board;

import java.util.List;
import java.util.ArrayList;

public class Point {
    public Pawn pawn;
    public List<Point> neighbours1;
    public List<Point> neighbours2;
    public Boolean isInGame;
    public Pair pos;

    public Point(Pair pos){
        this.pos = pos;
        this.isInGame = true;
        this.neighbours1 = new ArrayList<Point>();
        this.neighbours2 = new ArrayList<Point>();
        this.pawn = null;
    }

    public String toString(){
        return "Point: " + pos.x + " " + pos.y;
    }

    public void printNeighbours(){
        System.out.println("Neighbours of " + this.toString());
        for(Point p : neighbours1){
            System.out.println(p.toString());
        }
    }
}
