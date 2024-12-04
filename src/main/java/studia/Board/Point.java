package studia.Board;

import java.util.List;
import java.util.ArrayList;

public class Point {
    public Pawn pawn;
    public List<Point> neighbours1;
    public List<Point> neighbours2;
    public Boolean isInGame;
    int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
        this.isInGame = true;
        this.neighbours1 = new ArrayList<Point>();
        this.neighbours2 = new ArrayList<Point>();
        this.pawn = null;
    }

    public String toString(){
        return "Point: " + x + " " + y;
    }

    public void printNeighbours(){
        System.out.println("Neighbours of " + this.toString());
        for(Point p : neighbours1){
            System.out.println(p.toString());
        }
    }
}
