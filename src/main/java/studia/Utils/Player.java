package studia.Utils;

import java.util.List;
import java.util.ArrayList;

public class Player {
    public Point startCorner;
    public Point finishCorner;
    public int playerNumber;
    public int countPawns = 0;
    
    private int color;
    private List<Pawn> pawns = new ArrayList<Pawn>();

    public Player(int color) {
        countPawns = 0;
        this.color = color;
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

    public void setFinishCorner(Point finishCorner) {
        this.finishCorner = finishCorner;
    }

    public int getColor() {
        return color;
    }

}
