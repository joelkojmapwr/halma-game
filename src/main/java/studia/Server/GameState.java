package studia.Server;

public class GameState extends ServerState {
	private int currentPlayer = 0;
	
	GameState(Server server) {
		super(server);
		try {
			server.startGame();
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
