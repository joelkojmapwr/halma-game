package studia.Client;

import studia.Utils.Player;
import studia.Common.FXGame;
import java.io.IOException;


public class FXClient extends Client {

	protected FXGame game;

    public FXClient(String host, int port) throws IOException {
        super(host, port);
    }

    @Override
    public void startGame(int curplr) {
		Player[] plrs = new Player[nplayers];
		for(int i=0;i<nplayers;i++)
			plrs[i] = new Player(i);
		this.game = new FXGame(plrs, curplr);
		interpreter.setGame(game);
		ClientApp.client = this;
		new Thread(() -> ClientApp.launchApp(new String[0])).start();
	}

}
