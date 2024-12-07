package studia.Common;

import studia.Server.ServerPlayer;
import studia.Client.Client;


import java.util.Scanner;

public class BmovMessage extends Message {
	private Client client;
	
	public BmovMessage(int[] args, Client client) {
		this.client = client;
	}
	
	
	public void execute() {
		System.out.println("Invalid move, try again");
		Scanner in = new Scanner(System.in);
		int from = in.nextInt();
		int to = in.nextInt();
		client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), from, to);
	}
}
