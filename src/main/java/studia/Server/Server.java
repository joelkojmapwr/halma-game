package studia.Server;

import java.io.*;
import java.net.*;
import java.util.Date;

import studia.Common.MessageInterpreter;
import studia.Common.Message;
import studia.Common.Player;
import studia.Common.Game;

public class Server {
	private int PORT;
	private int nplayers;
	private Player[] connected;
	private int nconnected;
	private ServerSocket serverSocket;
	
	private Game game;
	
	//private ServerState state;
	
	private MessageInterpreter interpreter;
	
	
	
	public Server(int port, int players) throws IOException {
		PORT = port;
		
		if(players != 2 && players != 3 && players != 4 && players != 6)
			throw new IllegalArgumentException("Invalid players number!");
		
		nplayers = players;
		connected = new ServerPlayer[nplayers];
		nconnected = 0;
		
		interpreter = new MessageInterpreter(this);
		
		for(int i=0;i<nplayers;i++)
			connected[i] = new ServerPlayer(this, i);
		
		serverSocket = new ServerSocket(PORT);
		
		//changeState(new BeforeGameState(this));
		waitForConnection();
	}
	
	/*public void changeState(ServerState state) {
		this.state = state;
	}*/
	
	public int getPlayersNum() {
		return nplayers;
	}
	
	public int getConnected() {
		return nconnected;
	}
	
	public void waitForConnection() {
		try {
			System.out.printf("Waiting for players (%d/%d)\n", nconnected + 1, nplayers);
			Socket s = serverSocket.accept();
			
			sendToAll(Message.MSG_CONN, nconnected, nplayers);
				
			((ServerPlayer)connected[nconnected++]).setSocket(s);
			System.out.printf("Player connected (%d/%d)\n", nconnected, nplayers);
			((ServerPlayer)connected[nconnected - 1]).writeMessage(Message.MSG_YCON, nconnected, nplayers);
			if(nconnected < nplayers) waitForConnection();
			else {
				startGame();
				waitForMessages();
			}
			//state.onAccept();
		} catch (IOException ex) {
			System.err.println(ex);
		} catch(InterruptedException ex) {
			System.err.println(ex);
		}
	}
	
	public Game startGame() {
		int randomplayer = 0;
		game = new Game(connected, randomplayer);
		interpreter.setGame(game);
		sendToAll(Message.MSG_BEG, randomplayer);
		((ServerPlayer)game.getCurrentPlayer()).writeMessage(Message.MSG_YMOV);
		return game;
	}
	
	public void waitForMessages() throws InterruptedException {
		for(int i=0;i<nplayers;i++)
			((ServerPlayer)connected[i]).startReceiver();
		for(int i=0;i<nplayers;i++)
			((ServerPlayer)connected[i]).joinReceiver();
	}
	
	public void sendToAll(int... msg) {
		for(int i=0;i<nconnected;i++)
			((ServerPlayer)connected[i]).writeMessage(msg);
	}
	
	public void onMessage(Message msg) {
		msg.execute();
	}
	
	public void closeConnections() {
		for(int i=0;i<nconnected;i++)
			((ServerPlayer)connected[i]).closeSocket();
	}
	
	public void onClientDisconnect(ServerPlayer client) {
		int p = -1;
		for(int i=0;i<connected.length;i++)
			if(client == connected[i]) {
				p = i;
				break;
			}
		System.out.printf("Player %d disconnected\n", p);
		for(int i=0;i<nconnected;i++)
			if(!((ServerPlayer)connected[i]).disconnected)
				((ServerPlayer)connected[i]).writeMessage(Message.MSG_HUP, p);

		closeConnections();
	}
	
	public MessageInterpreter getInterpreter() {
		return interpreter;
	}
}
