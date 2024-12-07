package studia.Common;

import studia.Utils.Color;

public class HupMessage extends Message {
	private int who;
	
	public HupMessage(int[] args) {
		who = args[0];
	}
	
	public void execute() {
		System.out.printf("Player %d (%s) disconnected, game terminated\n", who, Color.colorName(who));
	}
}
