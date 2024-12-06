package studia.Common;

public class HupMessage extends Message {
	private int who;
	
	public HupMessage(int[] args) {
		who = args[0];
	}
	
	public void execute() {
		System.out.printf("Player %d disconnected, game terminated\n", who);
	}
}
