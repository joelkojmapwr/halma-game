package studia.Server;

import java.io.IOException;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import studia.Board.BoardBuilder;
import studia.Common.DAOGame;
import studia.Common.Game;
import studia.Common.Message;
import studia.DAO.GameData;
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
    }


    @Override
    public Game startGame() {
		Random rand = new Random();
		int startingPlayer = rand.nextInt(connected.length);
        int moredata = 0;
		
		BoardBuilder boardBuilder = new BoardBuilder(4, connected, 10);

        GameData gameData = new GameData();
        if (gameID != -1) {
            gameJDBCTemplate.setGameID(gameID);
            gameData = gameJDBCTemplate.getGameData();
            variant = gameData.getVariant();
            bots = gameData.getBotsNumber();
            nplayers = gameData.getPlayersNumber();
            startingPlayer = gameData.getStartingPlayer();
            moredata = gameData.getMoreData();
        }
        else {
            if(variant == Variant.CHAOS) {
                moredata = rand.nextInt();
            } else if (variant == Variant.YINYAN) {
                moredata = (startCorner[0] & 0xff) | ((startCorner[1] & 0xff) << 8);
            }
        }
		
		boardBuilder.setVariant(variant);
		
		if(variant == Variant.CHAOS) {
			boardBuilder.setSeed(moredata);
		} else if(variant == Variant.YINYAN) {
			boardBuilder.setYinCorners(moredata);
		}
		
		boardBuilder.build();
		
		studia.Utils.Color.YinYan = variant == Variant.YINYAN;
		
		game = new DAOGame(connected, startingPlayer, boardBuilder.getBoard());
		interpreter.setGame(game);
		
		for(int i=0;i<bots;i++) {
			((BotPlayer)connected[i]).setBoard(boardBuilder.getBoard());
			((BotPlayer)connected[i]).setGame(game);
		}
		
		sendToAll(Message.MSG_BEG, startingPlayer, variant, moredata);


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
            if (variant == Variant.CHAOS || variant == Variant.YINYAN) {
                gameJDBCTemplate.create(variant, bots, nplayers, startingPlayer, moredata);
            }
            else {
                gameJDBCTemplate.create(variant, bots, nplayers, startingPlayer);
            }
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
