package studia.Common;

import studia.Server.Server;
import studia.Server.ServerPlayer;

public class MoveMessage extends Message {
	private int plr, mov;
	private Game game;
	private Server server;
	
	public MoveMessage(int[] args, Game game, Server server) {
		this.plr = args[0];
		this.mov = args[1];
		this.game = game;
		this.server = server;
	}
	
	
	public void execute() {
		boolean result;
		Move m = new Move(mov);
		if(server == null) { //clientside
			result = game.playerMove(plr, m);
		} else {
			result = game.playerMove(sender, m);
			if(result) {
				server.sendToAll(Message.MSG_MOVE, plr, mov);
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
