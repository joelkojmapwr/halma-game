package studia.Common;

public class Move {
	public int from, to;
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public String toString() {
		if(from == to) return "stand";
		return String.valueOf(from) + " -> " + String.valueOf(to);
	}
	
}
