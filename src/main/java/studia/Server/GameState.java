/*package studia.Server;


import studia.Common.Game;

public class GameState extends ServerState {
	private Game game;
	
	GameState(Server server) {
		super(server);
		try {
			game = server.startGame();
			server.waitForMessages();
		}	catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onAccept() {}

	@Override
	public void onMessage() {
		
	}

	@Override
	public void onEnd() {
		
	}
}
*/
