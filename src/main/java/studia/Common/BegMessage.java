package studia.Common;

import studia.Client.Client;

public class BegMessage extends Message {
	private int cplr;
	private Client cli;
	
	public BegMessage(int[] args, Client client) {
		this.cplr = args[0];
		cli = client;
	}
	
	
	public void execute() {
		cli.startGame(cplr);
	}
}
