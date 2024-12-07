package studia.Common;

import studia.Utils.Color;

public class EndMessage extends Message {
	private int winner;
	
	public EndMessage(int[] args) {
		winner = args[0];
	}
	
	public void execute() {
		System.out.printf("Player %d (%s) won!\n", winner, Color.colorName(winner));
	}
}
