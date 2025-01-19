package studia.Common;

import studia.Client.Client;
/**
 * @see studia.Common.Message
 */
public class YmovMessage extends Message {
	private Client client;
	
	public YmovMessage(int[] args, Client client) {
		this.client = client;
	}
	
	
	public void execute() {
		handler.handleYmov();
		Move m = handler.getMove();
		client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), m.from, m.to);
	}
}
