package studia.Server;

import studia.Utils.Player;
import java.io.*;
import java.net.*;
import studia.Common.Message;

public class ServerPlayer extends Player {
	private Server server;
	private Thread Receiver;
	private boolean receiver_run = true;
	
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	private ServerPlayer thisref;
	
	public boolean disconnected = false;
	
	public ServerPlayer(Server server, int color) {
		super(color);
		this.server = server;
		thisref = this;
	}
	
	public void setSocket(Socket s) throws IOException {
		socket = s;
		
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
		
		Receiver = new Thread() {
			public void run() {
				try {
					while(receiver_run) {
						Message m = server.getInterpreter().interpret(inStream);
						m.setSender((Player) thisref);
						server.onMessage(m);
					}
				} catch(IOException e) {
					receiver_run = false;
					disconnected = true;
					server.onClientDisconnect(thisref);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};

	}
	
	public void closeSocket() {
		try {
			socket.close();
			disconnected = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeMessage(int... args) {
		try {
			for(int i: args)
				outStream.writeInt(i);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startReceiver() {
		Receiver.start();
	}
	
	public void joinReceiver() throws InterruptedException {
		Receiver.join();
	}
}


