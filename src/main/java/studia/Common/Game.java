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

/** This class is interface between client/server and game board and logic */
public class Game {
	private Player[] players;
	private int curplr, winner = -1;
        
	private Board board;
	private MoveHandler moveHandler;
	private WinChecker winChecker;
        
	
	public Game(Player[] players, int current, Board board) {
		this.players = players;
		curplr = current;
		
		this.board = board;
		
		moveHandler = new StandardMoveHandler(board);
		winChecker = new StandardWinChecker(10);
		
		board.printBoard();
	}
	
	/** Returns player number (ansi escape code color) */
	public int playerToColor(Player plr) {
		for(int i=0;i<players.length;i++)
			if(players[i] == plr)
			 return i;
		return -1;
	}
	
	/** Moves player with color p*/
	public boolean playerMove(int p, Move m) {
		if(p != curplr) return false;
		if(!moveHandler.newMove(m.from, m.to, players[p])) return false;
		
		board.printBoard();
		System.out.printf("Player %d (%s): %s\n", p, Color.colorName(p), m.toString());
		
		curplr = (curplr + 1) % players.length;
		if(winChecker.checkWin(players[p])) winner = p;
		
		return true;
	}
	
	/**Moves Player plr*/
	public boolean playerMove(Player plr, Move m) {
		int p = playerToColor(plr);
		if(p<0) return false;
		return playerMove(p, m);
	}
	
	/** @return player who makes move in this turn*/
	public Player getCurrentPlayer() {
		return players[curplr];
	}
	
	/** @return winner color or -1 if no winner*/
	public int getWinner() { //zwraca index zwyciezcy lub -1
		return winner;
	}
}
