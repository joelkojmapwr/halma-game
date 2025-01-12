package studia.Common;

import studia.Client.Client;
import studia.Board.Board;

public class BegMessage extends Message {
	private int cplr, variant, more;
	private Client cli;
	
	public BegMessage(int[] args, Client client) {
		this.cplr = args[0];
		this.variant = args[1];
		this.more = args[2];
		cli = client;
	}
	
	
	public void execute() {
		Board b = cli.startGame(cplr, variant, more);
		handler.handleBeg(cplr, b);
	}
}
