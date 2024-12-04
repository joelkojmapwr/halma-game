package studia.Common;

import java.io.*;

public abstract class Message {
	public final static int MSG_CONN = 0; //arg: int, int
	public final static int MSG_YCON = 1; //arg: int, int
	public final static int MSG_BEG = 2; //arg: 0
	public final static int MSG_MOVE = 3; //arg: ?
	public final static int MSG_END  = 4; //arg: ?
	
	public static int nargs[] = new int[] {2, 2, 0, 2, 0};
	
	public static Message interpret(DataInputStream is) throws IOException {
		int code = is.readInt();
		int[] args = null;
		if(nargs[code] > 0) {
			args = new int[nargs[code]];
			for(int i=0;i<args.length;i++)
				args[i] = is.readInt();
		}
		switch(code) {
			case Message.MSG_CONN: return new ConnMessage(args);
			case Message.MSG_YCON: return new YconMessage(args);
		}
		return null;
	}
	
	public abstract void execute();
}
