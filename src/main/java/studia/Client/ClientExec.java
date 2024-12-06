package studia.Client;

import java.io.*;

public class ClientExec {
	public static void main(String[] args) {
		try {
			Client c = new Client(9999);
			c.listen();
		} catch(EOFException e) {
			System.out.println("Server disconnected");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
  }
}
