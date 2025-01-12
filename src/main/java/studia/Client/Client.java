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


/**
 * Klasa klienta
 */
public class Client extends Thread {
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	private Game game;
	private int nplayers = 0, yournumber = -1;
	public boolean broken = false;
	
	
	MessageInterpreter interpreter;
	
	MessageHandler handler = new StdoutMessageHandler();
	
	/**
	* @param host server address
	* @param port server port
	*/
	public Client(String host, int port) throws IOException {
		socket = new Socket(host, port);
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
		interpreter = new MessageInterpreter(this);
	}
	
	/**
	* Sets client message handler
	* @see studia.Common.MessageHandler
	*/
	public void setHandler(MessageHandler handler) {
		this.handler = handler;
	}
	
	/**
	* Sets players number for client game
	*/
	public void setPlayersNumber(int n) {
		nplayers = n;
	}
	
	/**
	* Sets this client player number
	*/
	public void setYourNumber(int n) {
		yournumber = n;
	}
	
	/**
	* @return This client player number
	*/
	public int getYourNumber() {
		return yournumber;
	}
	
	/**
	* Blocking call that begins listening for messages from server
	*/
	public void listen() throws IOException {
		while(true) {
			Message m = interpreter.interpret(inStream);
			m.setHandler(handler);
			m.execute();
		}
	}
	
	/**
	* Non-blocking call that begins listening for messages from server
	*/
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
	
	/**
	* Variadic function that send message to server, first argument is message number, next are message arguments
	* @see studia.Common.Message
	*/
	public void writeMessage(int... args) {
		try {
			for(int i: args)
				outStream.writeInt(i);
		}
		catch (SocketException se) {}
		catch(IOException e) { e.printStackTrace(); }
	}
	
	/**
	* Starts game for client
	* @param curplr number of player who makes move in this turn
	* @param variant game variant
	* @param seed additional parameter, used to pass additional data for variants
	* @see studia.Utils.Variant
	*/
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
	
	/**
	* Closes client socket
	*/
	public void closeSocket() {
		try {
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
