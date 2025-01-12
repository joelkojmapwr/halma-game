package studia.Common;

import java.io.*;

import studia.Client.Client;
import studia.Common.Game;
import studia.Utils.Player;

public abstract class Message {
	public final static int MSG_CONN = 0; //CONNECTED arg: int pos, int total
	public final static int MSG_YCON = 1; //YOUCONNECTED arg: int pos , int total, int variant
	public final static int MSG_BEG  = 2; //BEGIN arg: int curplr, int variant, int seed
	public final static int MSG_MOVE = 3; //arg: int plr, int from, int to
	public final static int MSG_YMOV = 4; //YOURMOVE
	public final static int MSG_BMOV = 5; //BADMOVE
	public final static int MSG_END  = 6; //END arg: int winner
	public final static int MSG_HUP  = 7; //HANGUP arg: int disconnectedplr
	
	public final static int MSG_CORN  = 8; //CORNER, int (c->s pick, s->c reserved)
	
	public final static int nargs[] = new int[] {2, 3, 3, 3, 0, 0, 1, 1, 1};
	
	protected Player sender;
	protected MessageHandler handler;
	
	public abstract void execute();
	
	public void setSender(Player sender) {
		this.sender = sender;
	}
	
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}
}
