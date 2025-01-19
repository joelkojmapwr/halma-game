package studia.Common;

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
