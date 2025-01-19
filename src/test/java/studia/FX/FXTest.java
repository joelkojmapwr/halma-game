package studia.FX;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import studia.Board.Board;
import studia.Common.Utils;
import studia.Utils.Player;
import studia.Utils.FXPoint;
import studia.Board.FXBoardBuilder;
import studia.Utils.Point;

public class FXTest {
    

    /**
     * Test if point on FXGame board is FXPoint
     */
    @Test
    public void testFXPoint() {
        Player[] players = Utils.initPlayers(6);
        FXBoardBuilder builder = new FXBoardBuilder(4, players, 10);
        builder.build();

        Board board = builder.getBoard();
        for (Point p : board.validPointsMap.values()) {
            if (p instanceof FXPoint) {
                assertTrue(true);
            } else {
                assertFalse(true);
            }
        }
    }
    
}
