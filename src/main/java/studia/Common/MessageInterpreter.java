package studia.Common;

import java.io.*;

import studia.Client.Client;
import studia.Server.Server;
import studia.Common.Game;
import studia.Common.Player;

public class MessageInterpreter {
	private Game game;
	private Client client;
	private Server server;
	
	public MessageInterpreter(Client client) {
		this.client = client;
	}
	
	public MessageInterpreter(Server server) {
		this.server = server;
	}
	
	public Message interpret(DataInputStream is) throws IOException {
		int code = is.readInt();
		int[] args = null;
		if(Message.nargs[code] > 0) {
			args = new int[Message.nargs[code]];
			for(int i=0;i<args.length;i++)
				args[i] = is.readInt();
		}
		switch(code) {
			case Message.MSG_CONN: return new ConnMessage(args);
			case Message.MSG_YCON: return new YconMessage(args, client);
			case Message.MSG_BEG:  return new BegMessage(args, client);
			case Message.MSG_MOVE: return new MoveMessage(args, game, server);
			case Message.MSG_YMOV: return new YmovMessage(args, client);
			case Message.MSG_BMOV: return new BmovMessage(args, client);
			case Message.MSG_END:  return new EndMessage(args);
			case Message.MSG_HUP:  return new HupMessage(args);
		}
		return null;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
}
