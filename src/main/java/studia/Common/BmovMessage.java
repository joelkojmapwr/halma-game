package studia.Common;

import studia.Server.ServerPlayer;
import studia.Client.Client;

public class BmovMessage extends Message {
	private Client client;
	
	public BmovMessage(int[] args, Client client) {
		this.client = client;
	}
	
	
	public void execute() {
		handler.handleBmov();
		Move m = handler.getMove();
		
		client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), m.from, m.to);
	}
}
