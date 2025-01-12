package studia.Client;

import java.io.*;
import java.net.*;

import studia.Common.Message;
import studia.Common.MessageInterpreter;
import studia.Common.Game;
import studia.Utils.Player;
import studia.Utils.Variant;

import studia.Common.MessageHandler;
import studia.Common.StdoutMessageHandler;

import studia.Board.BoardBuilder;
import studia.Board.FXBoardBuilder;
import studia.Board.Board;

public class Client extends Thread {
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	private Game game;
	private int nplayers = 0, yournumber = -1;
	public boolean broken = false;
	
	
	MessageInterpreter interpreter;
	
	MessageHandler handler = new StdoutMessageHandler();
	
	public Client(String host, int port) throws IOException {
		socket = new Socket(host, port);
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
		interpreter = new MessageInterpreter(this);
	}
	
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
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
			m.setHandler(handler);
			m.execute();
		}
	}
	
	public void run() {
		while(true) {
			try {
				Message m = interpreter.interpret(inStream);
				m.setHandler(handler);
				m.execute();
			} catch(IOException e) {
				broken = true;
				break;
			}
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
	
	public Board startGame(int curplr, int variant, int seed) {
		Player[] plrs = new Player[nplayers];
		for(int i=0;i<nplayers;i++)
			plrs[i] = new Player(i);
		
		BoardBuilder boardBuilder = new FXBoardBuilder(4, plrs, 10);
		boardBuilder.setVariant(variant);
		if(variant == Variant.CHAOS) boardBuilder.setSeed(seed);
		else if(variant == Variant.YINYAN) boardBuilder.setYinCorners(seed);
		boardBuilder.build();
		
		studia.Utils.Color.YinYan = variant == Variant.YINYAN;
		
		game = new Game(plrs, curplr, boardBuilder.getBoard());
		interpreter.setGame(game);
		
		return boardBuilder.getBoard();
	}
	
	public void closeSocket() {
		try {
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
