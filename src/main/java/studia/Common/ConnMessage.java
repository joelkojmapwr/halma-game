package studia.Common;

import studia.Utils.Color;

public class ConnMessage extends Message {
	private int pos, total;
	
	public ConnMessage(int[] args) {
		this.pos = args[0];
		this.total = args[1];
	}
	
	public void execute() {
		System.out.printf("Player %d (%s) connected (%d/%d)\n", pos, Color.colorName(pos), pos+1, total);
	}
}
