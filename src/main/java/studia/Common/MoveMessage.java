package studia.Common;

import studia.Server.Server;
import studia.Server.ServerPlayer;

public class MoveMessage extends Message {
	private int plr, from, to;
	private Game game;
	private Server server;
	
	public MoveMessage(int[] args, Game game, Server server) {
		this.plr = args[0];
		this.from = args[1];
		this.to = args[2];
		this.game = game;
		this.server = server;
	}
	
	
	public void execute() {
		boolean result;
		Move m = new Move(from, to);
		if(server == null) { //clientside
			result = game.playerMove(plr, m);
		} else {
			result = game.playerMove(sender, m);
			if(result) {
				server.sendToAll(Message.MSG_MOVE, plr, from ,to);
				int winner = game.getWinner();
				if(winner >= 0) {
					server.sendToAll(Message.MSG_END, winner);
					server.closeConnections();
				} else
					((ServerPlayer)game.getCurrentPlayer()).writeMessage(Message.MSG_YMOV);
			} else ((ServerPlayer)sender).writeMessage(Message.MSG_BMOV);
		}
	}
}
