package studia.Common;

public class EndMessage extends Message {
	private int winner;
	
	public EndMessage(int[] args) {
		winner = args[0];
	}
	
	public void execute() {
		System.out.printf("Player %d won!\n", winner);
	}
}
