package studia.Common;


import studia.Utils.Player;

public abstract class Message {
	/**
	 * Message sent from server to clients when other client connects server
	 * Arguments: connected player number, total number of players
	 */
	public final static int MSG_CONN = 0; //CONNECTED arg: int pos, int total
	/**
	 * Message sent from server to client when this client connects server
	 * Arguments: connected player number, total number of players, game variant number
	 */
	public final static int MSG_YCON = 1; //YOUCONNECTED arg: int pos , int total, int variant
	/**
	 * Message sent from server to clients when game starts
	 * Arguments: current player (that makes move in this turn) number, variant number, extra info
	 */
	public final static int MSG_BEG  = 2; //BEGIN arg: int curplr, int variant, int seed
	/**
	 * Message sent from client to server and from server to all clients when client make move
	 * Arguments: player number, from, to
	 */
	public final static int MSG_MOVE = 3; //arg: int plr, int from, int to
	/**
	 * Message sent to client when it is his turn
	 * No arguments
	 */
	public final static int MSG_YMOV = 4; //YOURMOVE
	/**
	 * Message sent to client when he made bad move and has to try again
	 * No arguments
	 */
	public final static int MSG_BMOV = 5; //BADMOVE
	/**
	 * Message sent to clients when game ends
	 * Argument: winner
	 */
	public final static int MSG_END  = 6; //END arg: int winner
	/**
	 * Message sent to clients when one of clients disconnected, game is then terminated
	 * Argument: Number of client who disconnected
	 */
	public final static int MSG_HUP  = 7; //HANGUP arg: int disconnectedplr
	/**
	 * Message sent from server to client to command it to select starting corner. It is sent back from client to server when corner when corner is selected.
	 * Argument: corner reserved by oponent (when sending from server), selected corner (when sending from client)
	 */
	public final static int MSG_CORN  = 8; //CORNER, int (c->s pick, s->c reserved)
	
	public final static int nargs[] = new int[] {2, 3, 3, 3, 0, 0, 1, 1, 1};
	
	protected Player sender;
	protected MessageHandler handler;
	
	public abstract void execute();
	
	/**
	 * Sets message sender (serverside only)
	 */
	public void setSender(Player sender) {
		this.sender = sender;
	}
	
	/**
	 * @see studia.Common.MessageHandler
	 */
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}
}
