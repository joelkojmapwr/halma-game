package studia.Common;

import studia.Utils.Color;
/**
 * @see studia.Common.Message
 */
public class EndMessage extends Message {
	private int winner;
	
	public EndMessage(int[] args) {
		winner = args[0];
	}
	
	public void execute() {
		handler.handleWin(winner);
	}
}
