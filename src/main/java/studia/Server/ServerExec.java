package studia.Server;

import java.io.*;

public class ServerExec {
  public static void main(String[] args) {
		try {
			Server s = new Server(9999, 2);
		} catch (IOException ex) {
			System.err.println(ex);
		}
    
  }
}
