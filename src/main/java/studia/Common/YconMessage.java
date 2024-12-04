package studia.Common;

public class YconMessage extends Message {
	private int pos, total;
	
	public YconMessage(int[] args) {
		this.pos = args[0];
		this.total = args[1];
	}
	
	public void execute() {
		System.out.printf("Connected (%d/%d)\n", pos, total);
	}
}
