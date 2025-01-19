package studia.Common;

import studia.Client.Client;

/**
 * @see studia.Common.Message
 */
public class YconMessage extends Message {
	private int pos, total, variant;
	
	public YconMessage(int[] args, Client cli) {
		this.pos = args[0];
		this.total = args[1];
		this.variant = args[2];
		cli.setPlayersNumber(args[1]);
		cli.setYourNumber(args[0]-1);
	}
	
	public void execute() {
		handler.handleYcon(pos, total, variant);
		//System.out.printf("Connected (%d/%d). Your color is %s.\n", pos, total, Color.colorName(pos-1));
	}
}
