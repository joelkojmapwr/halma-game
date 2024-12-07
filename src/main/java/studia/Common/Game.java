package studia.Common;

import studia.Common.Move;

import java.util.Random;

public class Game {
	Player[] players;
	int curplr;
	
	int moves = 0;
	
	public Game(Player[] players, int current) {
		this.players = players;
		curplr = current;
	}
	
	public int playerToColor(Player plr) {
		for(int i=0;i<players.length;i++)
			if(players[i] == plr)
			 return i;
		return -1;
	}
	
	public boolean playerMove(int p, Move m) {
		if(p != curplr) return false;
		if(m.from < 0 || m.to < 0) return false;
		System.out.printf("Player %d move %s\n", p, m.toString());
		curplr = (curplr + 1) % players.length;
		moves++;
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
		Random rand = new Random();
		if(moves < 3) return -1;
		return rand.nextInt(players.length);
	}
}
