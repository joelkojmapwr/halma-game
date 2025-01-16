package studia.Server;

import java.io.*;

/**
 * Main server class
 */
public class ServerExec {
  public static void main(String[] args) {
		int PORT, nplayers, variant, bots = 0;
		
		if(args.length < 3) {
			System.out.println("PORT #players variant bots");
			return;
		}
		
		try {
			PORT = Integer.parseInt(args[0]);
			nplayers = Integer.parseInt(args[1]);
			variant = Integer.parseInt(args[2]);
			if(args.length > 3) bots = Integer.parseInt(args[3]);
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
		
		if(nplayers < bots) {
			System.out.println("More bots than players");
			return;
		}
		
		try {
			Server s = new Server(PORT, nplayers, variant, bots);
		} catch(IllegalArgumentException ex) {
			System.out.println("Invalid players number");
			return;
		} catch (IOException ex) {
			System.err.println(ex);
		}
    
  }
}
