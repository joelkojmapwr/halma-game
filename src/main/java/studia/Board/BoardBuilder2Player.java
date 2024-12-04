package studia.Board;

import java.util.List;

public class BoardBuilder2Player extends BoardBuilder {



    public BoardBuilder2Player(int triangleSize, List<Player> players) {
        super(triangleSize, players);
    }

    public void spawnPawns() {
        // starting from the corner
        PawnsSpawner pawnsSpawner = new PawnsSpawner(10);
        for (Player player : players){
            Point startPoint = board.points[player.startCorner.x][player.startCorner.y];
            try {
                pawnsSpawner.spawn(player, startPoint);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            board.printBoard();
        }
    }
}
