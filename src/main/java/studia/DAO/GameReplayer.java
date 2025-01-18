package studia.DAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import studia.Common.DAOGame;
import studia.Common.Game;
import studia.Common.Message;
import studia.Common.Move;
import studia.Server.Server;

import java.util.List;

public class GameReplayer {

    protected DAOGame game;

    protected MoveJDBCTemplate moveJDBCTemplate;
	protected ApplicationContext context;

    protected Server server;

    public GameReplayer(Game game, Server server) {
        this.game = (DAOGame) game;
        context = new ClassPathXmlApplicationContext("Beans.xml");
        moveJDBCTemplate = (MoveJDBCTemplate) context.getBean("MoveJDBCTemplate");
        this.server = server;
    }

    public void replay(int gameId) {

        
        
        List<MoveData> moves = moveJDBCTemplate.getMoves(gameId);

        game.setCurrentPlayer(moves.get(0).getPlayerNumber());

        for (MoveData moveData : moves) {
            int playerNumber = moveData.getPlayerNumber();
            int from = moveData.getFrom();
            int to = moveData.getTo();
            game.playerMove(playerNumber, new Move(from, to));
            server.sendToAll(Message.MSG_MOVE, playerNumber, from, to);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
