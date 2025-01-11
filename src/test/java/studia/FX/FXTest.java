package studia.FX;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import studia.Board.Board;
import studia.Common.FXGame;
import studia.Common.Utils;
import studia.Utils.Player;
import studia.Utils.FXPoint;

public class FXTest {
    

    /**
     * Test if point on FXGame board is FXPoint
     */
    @Test
    public void testFXPoint() {
        Player[] players = Utils.initPlayers(6);
        FXGame game = new FXGame(players, 0);
        Board board = game.getBoard();

        if (board.validPointsMap.get(0) instanceof FXPoint) {
            assertTrue(true);
        } else {
            assertFalse(true);
        }
    }

    
}
