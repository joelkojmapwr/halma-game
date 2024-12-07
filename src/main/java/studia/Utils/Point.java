package studia.Utils;

import java.util.List;

import studia.Board.Pawn;

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

    public void printNeighbours1(){
        System.out.println("Neighbours 1 of " + this.toString());
        for(Point p : neighbours1){
            System.out.println(p.toString());
        }
    }

    public void printNeighbours2(){
        System.out.println("Neighbours 2 of " + this.toString());
        for(Point p : neighbours2){
            System.out.println(p.toString());
        }
    }

    public Point getNeighbour2FromNeighbour1(Point neighbour1){
        for (Point neighbour2 : neighbour1.neighbours1) {
            // neighbour 2 generated this way should not be the same as the current point
            if (neighbour2 != this) {
                // if the distance is the same then they are in the same line
                if (Pair.distance(neighbour2.pos, neighbour1.pos)
                .equals(Pair.distance(neighbour1.pos, this.pos))) {
                    return neighbour2;
                }
            }
        }
        return null;
    }
}
