package studia.Server;

import java.io.*;

public class ServerExec {
  public static void main(String[] args) {
		int PORT, nplayers;
		
		if(args.length < 2) {
			System.out.println("PORT #players");
			return;
		}
		
		try {
			PORT = Integer.parseInt(args[0]);
			nplayers = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println("Invalid arguments");
			return;
		}
		
		try {
			Server s = new Server(PORT, nplayers);
		} catch(IllegalArgumentException ex) {
			System.out.println("Invalid players number");
			return;
		} catch (IOException ex) {
			System.err.println(ex);
		}
    
  }
}
