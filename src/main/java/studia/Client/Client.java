package studia.Client;

import java.io.*;
import java.net.*;

import studia.Common.Message;

public class Client {
	private Socket socket;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	
	public Client(int port) throws IOException {
		socket = new Socket("localhost", port);
		inStream = new DataInputStream(socket.getInputStream());
		outStream = new DataOutputStream(socket.getOutputStream());
	}
	
	public void listen() throws IOException {
		while(true) {
			Message m = Message.interpret(inStream);
			m.execute();
		}
	}
}
