package studia.Common;

import studia.Server.ServerPlayer;
import studia.Client.Client;

public class YmovMessage extends Message {
	private Client client;
	
	public YmovMessage(int[] args, Client client) {
		this.client = client;
	}
	
	
	public void execute() {
		System.out.println("Your turn");
		Move m = new Move();
		m.fromKeyboard();
		client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), m.from, m.to);
	}
}
