package studia.Server;

public class BeforeGameState extends ServerState {
    BeforeGameState(Server server) {
        super(server);
    }

    @Override
    public void onAccept() {
			if(server.getPlayersNum() != server.getConnected())
				server.waitForConnection();
			else
				server.changeState(new GameState(server));
    }

    @Override
    public void onMessage() {}

    @Override
    public void onEnd() {}
}
