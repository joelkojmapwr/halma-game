package studia.Common;

import studia.Board.Board;
import studia.Utils.Color;

import java.util.Scanner;
import java.util.InputMismatchException;
/** Message handler for terminal client */
public class StdoutMessageHandler implements MessageHandler {
	public void handleYcon(int pos, int total, int variant) {
		System.out.printf("Connected (%d/%d). Your color is %s. Variant %d\n", pos, total, Color.colorName(pos-1), variant);
	}
	
	public void handleConn(int pos, int total) {
		System.out.printf("Player %d (%s) connected (%d/%d)\n", pos, Color.colorName(pos), pos+1, total);
	}
	
	public void handleBeg(int you, Board b) {}
	
	public void handleMove(int plr, Move m) {}
	
	public void handleYmov() {
		System.out.println("Your turn");
	}
	
	public void handleBmov() {
		System.out.println("Invalid move, try again");
	}
	
	public void handleHup(int who) {
		System.out.printf("Player %d (%s) disconnected, game terminated\n", who, Color.colorName(who));
	}
	
	public void handleCorn(int corner) {
		System.out.printf("Select starting corner (reserved: %d)\n", corner);
	}
	
	public void handleWin(int winner) {
		System.out.printf("Player %d (%s) won!\n", winner, Color.colorName(winner));
	}
	
	public Move getMove() {
		Move m = new Move();
		m.fromKeyboard();
		return m;
	}
	
	public int getCorner() {
		Scanner in = new Scanner(System.in);
		int rv = 0;
		try { rv = in.nextInt(); } catch(InputMismatchException e) {}
		return rv;
	}
}
