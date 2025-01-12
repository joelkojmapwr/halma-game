package studia.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

import javafx.application.Platform;

import studia.Common.MessageHandler;
import studia.Board.Board;
import studia.Common.Move;
import studia.Utils.Color;

/**
 * Main class for client UI version
 */
public class ClientFX extends Application implements MessageHandler {
		
		private JoinScene js = new JoinScene(this);
		private LobbyScene ls = new LobbyScene(this);
		private SelectScene ss = new SelectScene(this);
		private GameScene gs;
		private Client client;
		
		private Stage STAGE;
		
		private int ycol;
		
    @Override
    public void start(Stage primaryStage) {
			STAGE = primaryStage;
			primaryStage.setTitle("HALMA");
			primaryStage.setScene(js.get());
			primaryStage.show();
    }
    
    @Override
		public void stop(){
				if(client != null)
					client.closeSocket();
		}


    public static void main(String[] args) {
        launch(args);
    }
    
    /** Try connect to server, throw exception on error
     * @param host server address
     * @param port server port
     */
    public void tryConnect(String host, int port) throws EOFException, IOException {
				client = new Client(host, port);
				client.setHandler(this);
				client.setUI();
				client.start();
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleYcon(int pos, int total, int variant) {
			Platform.runLater(() -> STAGE.setScene(ls.get()));
			Platform.runLater(() -> ls.updatePlayers(pos, total));
			Platform.runLater(() -> ls.updateVariant(variant));
			
			ycol = pos - 1;
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleConn(int pos, int total) {
			ls.updatePlayers(pos, total);
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleBeg(int cplr, Board b) {
			gs = new GameScene(this, b, ycol);
			Platform.runLater(() -> STAGE.setScene(gs.get()));
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleMove(int plr, Move m) {
			Platform.runLater(() -> gs.setMyTurn(false));
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleYmov() {
			Platform.runLater(() -> gs.setMyTurn(true));
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleBmov() {
			Platform.runLater(() -> gs.setMyTurn(true));
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleHup(int who) {
			Platform.runLater(() -> gs.showMessage("Player "+String.valueOf(who)+" ("+Color.colorName(who)+") disconnected, game terminated. Close this window"));
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleCorn(int corner) {
			Platform.runLater(() -> STAGE.setScene(ss.get()));
			Platform.runLater(() -> STAGE.setTitle("SELECT CORNER"));
			if(corner != -1)
				ss.setReserved(corner);
		}
		
		/** @see studia.Common.MessageHandler */
		public void handleWin(int winner) {
			Platform.runLater(() -> gs.showMessage("Player "+String.valueOf(winner)+" ("+Color.colorName(winner)+") won!\n"));
		}
		
		/** @see studia.Common.MessageHandler */
		public int getCorner() {
			int c = ss.getCorner();
			Platform.runLater(() -> STAGE.setScene(ls.get()));
			Platform.runLater(() -> STAGE.setTitle("HALMA"));
			return c;
		}
		
		/** @see studia.Common.MessageHandler */
		public Move getMove() {
			return gs.getMove();
		}
}
