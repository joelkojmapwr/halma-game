package studia.Common;
import studia.Board.Board;
import studia.Common.Move;
/**Methods from these interface are called by messages
 * @see studia.Common.StdoutMessageInterpreter
 */
public interface MessageHandler {
	public void handleYcon(int pos, int total, int variant);
	public void handleConn(int pos, int total);
	public void handleBeg(int you, Board b);
	public void handleMove(int plr, Move m);
	public Move getMove();
	public void handleYmov();
	public void handleBmov();
	public void handleHup(int who);
	public void handleCorn(int corner);
	public void handleWin(int winner);
	public int getCorner();
}
