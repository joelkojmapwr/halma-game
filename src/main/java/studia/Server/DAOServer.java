package studia.Server;

import java.io.IOException;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import studia.Board.BoardBuilder;
import studia.Common.DAOGame;
import studia.Common.Game;
import studia.Common.Message;
import studia.DAO.GameJDBCTemplate;
import studia.DAO.GameReplayer;
import studia.Observer.GameDAOObserver;
import studia.Utils.Variant;

public class DAOServer extends Server {

    protected GameJDBCTemplate gameJDBCTemplate;
	protected ApplicationContext context;
    protected int gameID = -1;

    public DAOServer(int port, int players, int variant, int bots, int gameID) throws IOException {
        super(port, players, variant, bots);
        this.gameID = gameID;
        context = new ClassPathXmlApplicationContext("Beans.xml");
		gameJDBCTemplate = (GameJDBCTemplate) context.getBean("GameJDBCTemplate");
        System.out.println("Hello from DAOServer constructor");
    }


    @Override
    public Game startGame() {
        System.out.println("Hello from DAOServer startGame");
		Random rand = new Random();
		int randomplayer = rand.nextInt(connected.length);
		
		BoardBuilder boardBuilder = new BoardBuilder(4, connected, 10);
		int moredata = 0;
		boardBuilder.setVariant(variant);
		
		if(variant == Variant.CHAOS) {
			moredata = rand.nextInt();
			boardBuilder.setSeed(moredata);
		} else if(variant == Variant.YINYAN) {
			moredata = (startCorner[0] & 0xff) | ((startCorner[1] & 0xff) << 8);
			boardBuilder.setYinCorners(moredata);
		}
		
		boardBuilder.build();
		
		studia.Utils.Color.YinYan = variant == Variant.YINYAN;
		
		game = new DAOGame(connected, randomplayer, boardBuilder.getBoard());
		interpreter.setGame(game);
		
		for(int i=0;i<bots;i++) {
			((BotPlayer)connected[i]).setBoard(boardBuilder.getBoard());
			((BotPlayer)connected[i]).setGame(game);
		}
		
		sendToAll(Message.MSG_BEG, randomplayer, variant, moredata);


        /**
         * Load game with gameid from database
         */
        if (gameID != -1) {
            GameReplayer gameReplayer = new GameReplayer(game, (Server) this);
            gameReplayer.replay(gameID);
        }

        /**
         * This is a new game so start saving moves to database
         */
        else {
            // this is a new game so create a new record and new gameid in the database
            gameJDBCTemplate.create(variant, bots, nplayers, randomplayer);
            gameJDBCTemplate.setGameID();
            gameID = gameJDBCTemplate.getGameID();
        }
        
        System.out.println("GameID: " + gameID);
        GameDAOObserver gameDAOObserver = new GameDAOObserver(gameID);
        ((DAOGame)game).addObserver(gameDAOObserver);
        //System.out.println("Added observer to game");


        

        // save new game to database
        
		((ServerPlayer)game.getCurrentPlayer()).writeMessage(Message.MSG_YMOV);
		return game;
	}
}
