package studia.Server;

import java.io.*;
import java.net.*;
import java.util.Date;

import studia.Common.Message;

public class Server {
	private int PORT;
	private int nplayers;
	private Player[] connected;
	private int nconnected;
	private ServerSocket serverSocket;
	
	private ServerState state;
	
	
	
	public Server(int port, int players) throws IOException {
		PORT = port;
		if(players != 2 && players != 3 && players != 4 && players != 6)
			throw new IllegalArgumentException("Invalid players number!");
		nplayers = players;
		connected = new Player[nplayers];
		nconnected = 0;
		
		for(int i=0;i<nplayers;i++)
			connected[i] = new Player(this);
		
		serverSocket = new ServerSocket(PORT);
		
		changeState(new BeforeGameState(this));
		waitForConnection();
	}
	
	public void changeState(ServerState state) {
		this.state = state;
	}
	
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
			
			for(int i=0;i<nconnected;i++)
				connected[i].writeMessage(Message.MSG_CONN, nconnected, nplayers);
				
			connected[nconnected++].setSocket(s);
			System.out.printf("Player connected (%d/%d)\n", nconnected, nplayers);
			connected[nconnected - 1].writeMessage(Message.MSG_YCON, nconnected, nplayers);
			state.onAccept();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	public void startGame() throws InterruptedException {
		for(int i=0;i<nplayers;i++) {
			connected[i].start();
			connected[i].join();
		}
	}
}
