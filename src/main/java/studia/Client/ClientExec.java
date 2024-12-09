package studia.Client;

import java.io.*;

public class ClientExec {
	public static void main(String[] args) {
		int PORT;
		
		if(args.length < 2) {
			System.out.println("host PORT");
			return;
		}
		
		try {
			PORT = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println("Invalid arguments");
			return;
		}
		
		try {
			Client c = new Client(args[0], PORT);
			c.listen();
		} catch(EOFException e) {
			System.out.println("Server disconnected");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
  }
}
