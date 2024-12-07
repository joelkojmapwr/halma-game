package studia.Common;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Move {
	public int from, to;
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public Move() {}
	
	public String toString() {
		if(from == to) return "stand";
		return String.valueOf(from) + " -> " + String.valueOf(to);
	}
	
	public void fromKeyboard() {
		Scanner in = new Scanner(System.in);
		try {
			this.from = in.nextInt();
			this.to = in.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Invalid input, try again");
			fromKeyboard();
		}
	}
	
}
