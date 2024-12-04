package studia.Server;

import java.io.*;
import java.net.*;
import studia.Common.Message;

public class Client extends Thread {
	protected Server server;
	
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	public Client(Server server) {
		this.server = server;
	}
	
	public void setSocket(Socket s) throws IOException {
		socket = s;
		
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void writeMessage(int... args) throws IOException {
		for(int i: args)
			outStream.writeInt(i);
	}
	
	public void run() {
		try {
			//server.onMessage((Player) this, inStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
