package studia.Client;

import java.io.*;
import java.net.*;

import studia.Common.Message;
import studia.Common.MessageInterpreter;
import studia.Common.Game;
import studia.Utils.Player;

public class Client {
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	private Game game;
	private int nplayers = 0, yournumber = -1;
	
	MessageInterpreter interpreter;
	
	public Client(int port) throws IOException {
		socket = new Socket("localhost", port);
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
		interpreter = new MessageInterpreter(this);
	}
	
	public void setPlayersNumber(int n) {
		nplayers = n;
	}
	
	public void setYourNumber(int n) {
		yournumber = n;
	}
	
	public int getYourNumber() {
		return yournumber;
	}
	
	public void listen() throws IOException {
		while(true) {
			Message m = interpreter.interpret(inStream);
			m.execute();
		}
	}
	
	public void writeMessage(int... args) {
		try {
			for(int i: args)
				outStream.writeInt(i);
		}
		catch (SocketException se) {}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	public void startGame(int curplr) {
		Player[] plrs = new Player[nplayers];
		for(int i=0;i<nplayers;i++)
			plrs[i] = new Player(i);
		game = new Game(plrs, curplr);
		interpreter.setGame(game);
	}
}
