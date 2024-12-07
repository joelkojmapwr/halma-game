package studia.Client;

import java.io.*;

public class ClientExec {
	public static void main(String[] args) {
		int PORT;
		
		if(args.length < 1) {
			System.out.println("PORT");
			return;
		}
		
		try {
			PORT = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.println("Invalid arguments");
			return;
		}
		
		try {
			Client c = new Client(PORT);
			c.listen();
		} catch(EOFException e) {
			System.out.println("Server disconnected");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
  }
}
