package studia.Board;

import java.util.List;

@Deprecated
public class BoardBuilder2Player extends BoardBuilder {



    public BoardBuilder2Player(int triangleSize, int playerNumber) {
        super(triangleSize, playerNumber);
    }

    public void spawnPawns() {
        // starting from the corner
        PawnsSpawner pawnsSpawner = new PawnsSpawner(10);
        
    }
}
