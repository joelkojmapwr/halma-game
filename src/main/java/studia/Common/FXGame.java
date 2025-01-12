package studia.Common;

import studia.Board.FXBoardBuilder;
import studia.Client.ClientApp;
import studia.MoveHandler.StandardMoveHandler;
import studia.Utils.Color;
import studia.Utils.FXPoint;
import studia.Utils.Player;
import studia.winChecker.StandardWinChecker;
import javafx.application.Platform;
import studia.Board.Board;

public class FXGame extends Game {
    

	public FXGame(Player[] players, int current) {
		super();
		this.players = players;
		curplr = current;
		
		FXBoardBuilder boardBuilder = new FXBoardBuilder(4, players, 10);
		boardBuilder.build();
		
		board = boardBuilder.getBoard();
		moveHandler = new StandardMoveHandler(board);
		winChecker = new StandardWinChecker(10);
	}

    @Override
    public boolean playerMove(int p, Move m) {
		if(p != curplr) return false;
		if(!moveHandler.newMove(m.from, m.to, players[p])) return false;
        System.out.printf("Player %d (%s): %s\n", p, Color.colorName(p), m.toString());
		// update UI
		Platform.runLater(() -> updatePointCircles(m));
		ClientApp.client.currentPlayer = (p + 1) % players.length;
		Platform.runLater(() -> ClientApp.updateUI());

		curplr = (curplr + 1) % players.length;
		if(winChecker.checkWin(players[p])) winner = p;
		
		return true;
	}


	protected void updatePointCircles(Move m) {
		FXPoint oldPoint = (FXPoint) board.validPointsMap.get(m.from);
		FXPoint newPoint = (FXPoint) board.validPointsMap.get(m.to);
		oldPoint.updateCircle();
		newPoint.updateCircle();
	}

    public Board getBoard() {
        return board;
    }

}
