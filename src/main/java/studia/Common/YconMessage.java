package studia.Common;

import studia.Client.Client;
import studia.Utils.Color;

public class YconMessage extends Message {
	private int pos, total, npl;
	private Client c;
	
	public YconMessage(int[] args, Client cli) {
		this.pos = args[0];
		this.total = args[1];
		cli.setPlayersNumber(args[1]);
		cli.setYourNumber(args[0]-1);
	}
	
	public void execute() {
		System.out.printf("Connected (%d/%d). Your color is %s.\n", pos, total, Color.colorName(pos-1));
	}
}
