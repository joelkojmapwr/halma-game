package studia.Common;

import studia.Common.Move;
import studia.Utils.Player;

import studia.MoveHandler.MoveHandler;
import studia.MoveHandler.StandardMoveHandler;
import studia.winChecker.WinChecker;
import studia.winChecker.StandardWinChecker;
import studia.Board.BoardBuilder;
import studia.Board.Board;

import studia.Utils.Color;

public class Game {
	private Player[] players;
	private int curplr, winner = -1;
        
	private Board board;
	private MoveHandler moveHandler;
	private WinChecker winChecker;
        
	
	public Game(Player[] players, int current) {
		this.players = players;
		curplr = current;
		
		BoardBuilder boardBuilder = new BoardBuilder(4, players, 10);
		boardBuilder.build();
		
		board = boardBuilder.getBoard();
		moveHandler = new StandardMoveHandler(board);
		winChecker = new StandardWinChecker(10);
		
		board.printBoard();
	}
	
	public int playerToColor(Player plr) {
		for(int i=0;i<players.length;i++)
			if(players[i] == plr)
			 return i;
		return -1;
	}
	
	public boolean playerMove(int p, Move m) {
		if(p != curplr) return false;
		if(!moveHandler.newMove(m.from, m.to, players[p])) return false;
		
		board.printBoard();
		System.out.printf("Player %d (%s): %s\n", p, Color.colorName(p), m.toString());
		
		curplr = (curplr + 1) % players.length;
		if(winChecker.checkWin(players[p])) winner = p;
		
		return true;
	}
	
	public boolean playerMove(Player plr, Move m) {
		int p = playerToColor(plr);
		if(p<0) return false;
		return playerMove(p, m);
	}
	
	public Player getCurrentPlayer() {
		return players[curplr];
	}
	
	public int getWinner() { //zwraca index zwyciezcy lub -1
		return winner;
	}
}
