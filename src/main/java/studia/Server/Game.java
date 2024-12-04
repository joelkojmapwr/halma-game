package studia.Server;

import studia.Common.Move;

public class Game {
	Player[] players;
	int curplr;
	public Game(Player[] players) {
		this.players = players;
		curplr = 0; // wylosuj go
	}
	
	public boolean playerMove(Player plr, Move m) {
		int p = -1;
		for(int i=0;i<players.length;i++)
			if(players[i] == plr) {
			 p = i; 
			 break;
		 }
		System.out.printf("Player %d move %s\n", p, m.toString());
		return true;
	}
}
