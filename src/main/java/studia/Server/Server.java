package studia.Server;

import java.io.*;
import java.net.*;
import java.util.Date;

import studia.Common.MessageInterpreter;
import studia.DAO.GameJDBCTemplate;
import studia.Common.Message;
import studia.Utils.Player;
import studia.Common.Game;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import studia.Utils.Variant;

import studia.Board.BoardBuilder;

/**
 * Server class, it lets client to connect and play Trylma */
public class Server {
	private int PORT;
	private int nplayers;
	private int variant;
	private int bots;
	private Player[] connected;
	private int nconnected;
	private ServerSocket serverSocket;
	
	private GameJDBCTemplate gameJDBCTemplate;
	private ApplicationContext context;
	
	private Game game;
	
	
	private MessageInterpreter interpreter;
	
	private int[] startCorner = {-1, -1};
	
	
	/**
	 * @param players number of players
	 * @param variant variant number
	 * @see studia.Utils.Variant
	 */
	public Server(int port, int players, int variant, int bots) throws IOException {
		PORT = port;
		this.variant = variant;
		this.bots = bots;
		if(players != 2 && players != 3 && players != 4 && players != 6)
			throw new IllegalArgumentException("Invalid players number!");
		
		nplayers = players;
		connected = new ServerPlayer[nplayers];
		nconnected = 0;
		
		interpreter = new MessageInterpreter(this);
		
		for(int i=0;i<bots;i++)
			connected[i] = new BotPlayer(this, i);
		nconnected = bots;
		
		for(int i=bots;i<nplayers;i++)
			connected[i] = new ServerPlayer(this, i);
		
		serverSocket = new ServerSocket(PORT);
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		gameJDBCTemplate = (GameJDBCTemplate) context.getBean("gameJDBCTemplate");
		
		waitForConnection();
	}
	
	/** Returns number of players */
	public int getPlayersNum() {
		return nplayers;
	}
	
	/** Returns number of connected players */
	public int getConnected() {
		return nconnected;
	}
	
	/** Waits for player to connect, if last player connects starts the game */
	public void waitForConnection() {
		try {
			if(nconnected < nplayers) {
				System.out.printf("Waiting for players (%d/%d)\n", nconnected + 1, nplayers);
				Socket s = serverSocket.accept();
				
				sendToAll(Message.MSG_CONN, nconnected, nplayers);
					
				((ServerPlayer)connected[nconnected++]).setSocket(s);
				System.out.printf("Player connected (%d/%d)\n", nconnected, nplayers);
				((ServerPlayer)connected[nconnected - 1]).writeMessage(Message.MSG_YCON, nconnected, nplayers, variant);
			}
			if(nconnected < nplayers) waitForConnection();
			else {
				if(variant != 2) startGame();
				else sendToAll(Message.MSG_CORN, -1);
				waitForMessages();
			}
		} catch (IOException ex) {
			System.err.println(ex);
		} catch(InterruptedException ex) {
			System.err.println(ex);
		}
	}
	
	/** Starts the game */
	public Game startGame() {
		Random rand = new Random();
		int randomplayer = rand.nextInt(connected.length);
		
		BoardBuilder boardBuilder = new BoardBuilder(4, connected, 10);
		int moredata = 0;
		boardBuilder.setVariant(variant);
		
		if(variant == Variant.CHAOS) {
			moredata = rand.nextInt();
			boardBuilder.setSeed(moredata);
		} else if(variant == Variant.YINYAN) {
			moredata = (startCorner[0] & 0xff) | ((startCorner[1] & 0xff) << 8);
			boardBuilder.setYinCorners(moredata);
		}
		
		boardBuilder.build();
		
		studia.Utils.Color.YinYan = variant == Variant.YINYAN;
		
		game = new Game(connected, randomplayer, boardBuilder.getBoard());
		interpreter.setGame(game);
		
		for(int i=0;i<bots;i++) {
			((BotPlayer)connected[i]).setBoard(boardBuilder.getBoard());
			((BotPlayer)connected[i]).setGame(game);
		}
		// save new game to database
		gameJDBCTemplate.create(variant, bots, nplayers, randomplayer);	
		sendToAll(Message.MSG_BEG, randomplayer, variant, moredata);
		((ServerPlayer)game.getCurrentPlayer()).writeMessage(Message.MSG_YMOV);
		return game;
	}
	
	/** Waits for messages from clients, blocking call */
	public void waitForMessages() throws InterruptedException {
		for(int i=0;i<nplayers;i++)
			((ServerPlayer)connected[i]).startReceiver();
		for(int i=0;i<nplayers;i++)
			((ServerPlayer)connected[i]).joinReceiver();
	}
	
	/** Send message to all clients
	 * @param msg @see studia.Client.Client#writeMessage
	 */
	public void sendToAll(int... msg) {
		for(int i=0;i<nconnected;i++)
			((ServerPlayer)connected[i]).writeMessage(msg);
	}
	
	/**
	 *  Called on message received
	 */
	public void onMessage(Message msg) {
		msg.execute();
	}
	
	/**
	 *	Closes connections with clients
	 */
	public void closeConnections() {
		for(int i=0;i<nconnected;i++)
			((ServerPlayer)connected[i]).closeSocket();
	}
	
	/**
	 *	Called on client disconnected
	 */
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
	
	/**
	 *	@see studia.Common.MessageInterpreter
	 */
	public MessageInterpreter getInterpreter() {
		return interpreter;
	}
	
	/**
	 * @return player number
	 */
	private int playerToInt(Player p) {
		for(int i=0;i<nconnected;i++)
			if(p == connected[i]) return i;
		return -1;
	}
	
	/**
	 * sets player start corner
	 */
	public boolean setStartCorner(Player player, int corner) {
		int plr = playerToInt(player);
		int rplr = (plr == 1) ? 0 : 1;
		if(startCorner[rplr] == corner)
			return false;
		startCorner[plr] = corner;
		if(startCorner[rplr] != -1 && startCorner[plr] != -1) startGame();
		return true;
	}
	
	/**
	 * returns corner reserved by player oponent or -1 if oponent didn't reserve any yet
	 */
	public int getReservedCorner(Player player) {
		int plr = playerToInt(player);
		int rplr = (plr == 1) ? 0 : 1;
		return startCorner[rplr];
	}
}
