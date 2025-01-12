package studia.Common;

import studia.Utils.Color;
import studia.Server.Server;
import studia.Client.Client;
import studia.Server.ServerPlayer;

public class CornMessage extends Message {
	private int corner;
	private Server server;
	private Client client;
	
	public CornMessage(int[] args, Server server, Client client) {
		corner = args[0];
		this.server = server;
		this.client = client;
	}
	
	public void execute() {
		if(server == null) {
			handler.handleCorn(corner);
			int corner = handler.getCorner();
			client.writeMessage(Message.MSG_CORN, corner);
		} else {
			if(!server.setStartCorner(sender, corner))
				((ServerPlayer)sender).writeMessage(Message.MSG_CORN, server.getReservedCorner(sender));
		}
	}
}
