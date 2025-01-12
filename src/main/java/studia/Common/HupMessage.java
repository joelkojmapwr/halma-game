package studia.Common;

import studia.Utils.Color;
/**
 * @see studia.Common.Message
 */
public class HupMessage extends Message {
	private int who;
	
	public HupMessage(int[] args) {
		who = args[0];
	}
	
	public void execute() {
		handler.handleHup(who);
	}
}
