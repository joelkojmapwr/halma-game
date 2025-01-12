package studia.Server;

import java.io.*;

/**
 * Main server class
 */
public class ServerExec {
  public static void main(String[] args) {
		int PORT, nplayers, variant;
		
		if(args.length < 3) {
			System.out.println("PORT #players variant");
			return;
		}
		
		try {
			PORT = Integer.parseInt(args[0]);
			nplayers = Integer.parseInt(args[1]);
			variant = Integer.parseInt(args[2]);
		} catch (Exception e) {
			System.out.println("Invalid arguments");
			return;
		}
		
		if(variant < 0 || variant > 2) {
			System.out.println("Invalid variant");
			return;
		}
		
		if(variant == 2 && nplayers != 2) {
			System.out.println("only 2 players can play YINYAN");
			return;
		}
		
		try {
			Server s = new Server(PORT, nplayers, variant);
		} catch(IllegalArgumentException ex) {
			System.out.println("Invalid players number");
			return;
		} catch (IOException ex) {
			System.err.println(ex);
		}
    
  }
}
