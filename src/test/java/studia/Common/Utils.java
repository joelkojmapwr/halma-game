package studia.Common;

import studia.Board.Board;
import studia.Board.BoardBuilder;
import studia.Utils.Player;

public class Utils {
    
    public static Board defaultBoard(int playerNumber) {
        Player[] players = initPlayers(playerNumber);
        int trianglesize = 4;
        BoardBuilder boardBuilder = new BoardBuilder(trianglesize, players, 10);
        boardBuilder.build();
        Board board = boardBuilder.getBoard();
        return board;
    }

    public static Player[] initPlayers(int playerNumber) {
        Player[] players = new Player[playerNumber];
        for (int i = 0; i<playerNumber; i++) {
            players[i] = new Player(i+1);
        }
        return players;
    }

}
