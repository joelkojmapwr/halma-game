package studia.Board;

import java.util.List;

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
    }
}
