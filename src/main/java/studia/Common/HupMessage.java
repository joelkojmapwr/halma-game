package studia.Common;

import studia.Utils.Color;

public class HupMessage extends Message {
	private int who;
	
	public HupMessage(int[] args) {
		who = args[0];
	}
	
	public void execute() {
		handler.handleHup(who);
	}
}
