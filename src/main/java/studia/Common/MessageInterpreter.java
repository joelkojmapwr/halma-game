package studia.Common;

import java.io.*;

import studia.Client.Client;
import studia.Server.Server;
import studia.Common.Game;
import studia.Utils.Player;

/** This class interprets data from socket and returns message classes
 * @see studia.Common.Message
 */
public class MessageInterpreter {
	private Game game;
	private Client client;
	private Server server;
	private MessageHandler handler = new StdoutMessageHandler();
	
	public MessageInterpreter(Client client) {
		this.client = client;
	}
	
	public MessageInterpreter(Server server) {
		this.server = server;
	}
	
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}
	
	public Message interpret(DataInputStream is) throws IOException {
		int code = is.readInt();
		int[] args = null;
		if(Message.nargs[code] > 0) {
			args = new int[Message.nargs[code]];
			for(int i=0;i<args.length;i++)
				args[i] = is.readInt();
		}
		Message msg = null;
		switch(code) {
			case Message.MSG_CONN: msg = new ConnMessage(args); break;
			case Message.MSG_YCON: msg = new YconMessage(args, client); break;
			case Message.MSG_BEG:  msg = new BegMessage(args, client); break;
			case Message.MSG_MOVE: msg = new MoveMessage(args, game, server); break;
			case Message.MSG_YMOV: msg = new YmovMessage(args, client); break;
			case Message.MSG_BMOV: msg = new BmovMessage(args, client); break;
			case Message.MSG_END:  msg = new EndMessage(args); break;
			case Message.MSG_HUP:  msg = new HupMessage(args); break;
			case Message.MSG_CORN: msg = new CornMessage(args, server, client); break;
		}
		if(msg == null) return null;
		msg.setHandler(handler);
		
		return msg;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}
