package studia.Common;

import studia.Server.ServerPlayer;
import studia.Client.Client;


import java.util.Scanner;

public class YmovMessage extends Message {
	private Client client;
	
	public YmovMessage(int[] args, Client client) {
		this.client = client;
	}
	
	
	public void execute() {
		//do zmiany
		System.out.println("Your turn");
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		client.writeMessage(Message.MSG_MOVE, client.getYourNumber(), num);
	}
}
