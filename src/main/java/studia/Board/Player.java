package studia.Board;

import java.util.List;
import java.util.ArrayList;

public class Player {
    public Point startCorner;
    public Point finishCorner;
    public int playerNumber;
    public int countPawns = 0;
    public int color;
    public List<Pawn> pawns = new ArrayList<Pawn>();

    public Player(int color) {
        countPawns = 0;
        this.color = color;
    }

    @Deprecated
    public Player(Point startCorner, int color){
        countPawns = 0;
        this.color = color;
        this.startCorner = startCorner;
    }

    public Pawn spawnNewPawn(){
        Pawn pawn = new Pawn(color);
        this.pawns.add(pawn);
        countPawns++;
        return pawn;
    }

    public void setStartCorner(Point startCorner) {
        this.startCorner = startCorner;
    }

}
