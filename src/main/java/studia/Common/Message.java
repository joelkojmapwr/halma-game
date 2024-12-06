package studia.Common;

import java.io.*;

import studia.Client.Client;
import studia.Common.Game;
import studia.Common.Player;

public abstract class Message {
	public final static int MSG_CONN = 0; //CONNECTED arg: int pos, int total
	public final static int MSG_YCON = 1; //YOUCONNECTED arg: int pos , int total
	public final static int MSG_BEG  = 2; //BEGIN arg: int curplr
	public final static int MSG_MOVE = 3; //arg: int plr, int data (shall be replaced with from, to)
	public final static int MSG_YMOV = 4; //YOURMOVE
	public final static int MSG_BMOV = 5; //BADMOVE
	public final static int MSG_END  = 6; //END arg: int winner
	public final static int MSG_HUP  = 7; //END arg: int disconnectedplr
	
	public final static int nargs[] = new int[] {2, 2, 1, 2, 0, 0, 1, 1};
	
	protected Player sender;
	
	public abstract void execute();
	
	public void setSender(Player sender) {
		this.sender = sender;
	}
}
