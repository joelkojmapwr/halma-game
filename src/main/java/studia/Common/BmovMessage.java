package studia.Common;

import studia.Server.ServerPlayer;
import studia.Client.Client;

public class BmovMessage extends Message {
	private Client client;
	
	public BmovMessage(int[] args, Client client) {
		this.client = client;
	}
	
	
	public void execute() {
		System.out.println("Invalid move, try again");
		Move m = new Move();
		m.fromKeyboard();
		client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), m.from, m.to);
	}
}
