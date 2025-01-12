package studia.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import studia.MoveHandler.MoveHandler;
import studia.MoveHandler.PseudoMoveHandler;
import studia.Utils.Player;
import studia.winChecker.StandardWinChecker;
import studia.winChecker.WinChecker;

/**
 * Unit test for simple App.
 */
public class ChaosSpawnTest {

    @Test
    public void testChaosSpawner() {
            int trianglesize = 4;
            Player[] players = initPlayers(6);
            BoardBuilder boardBuilder = new BoardBuilder(trianglesize, players, 10);
            boardBuilder.setVariant(1);
            boardBuilder.build();
            
            int[] notAllowed = {0,1,2,3,4,5,6,7,8,9,
							10,11,12,13,23,24,25,35,36,46,
							19,20,21,22,32,33,34,44,45,55,
							65,75,76,86,87,88,98,99,100,101,
							74,84,85,95,96,97,107,108,109,110,
							111,112,113,114,115,116,117,118,119,120};
							
						
						for(int i: notAllowed)
							assertTrue(boardBuilder.getBoard().validPointsMap.get(i).pawn == null);
    }

    public Player[] initPlayers(int playerNumber) {
        Player[] players = new Player[playerNumber];
        for (int i = 0; i<playerNumber; i++) {
            players[i] = new Player(i+1);
        }
        return players;
    }
}
