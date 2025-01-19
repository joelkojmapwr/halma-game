package studia.Client;
import javafx.scene.Scene;
import javafx.scene.control.Label;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import studia.Board.Board;
import studia.Board.GUIBoardBuilder;
import studia.Utils.FXPoint;
import studia.Utils.Point;
import studia.Common.Move;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javafx.scene.shape.Circle;

import studia.MoveHandler.StandardMoveHandler;

/** UI Scene that handles communication between user and game */
public class GameScene {
	
	private int sceneWidth = 800;
	private int sceneHeight = 800;
	
	private Scene scene;
	
	private Label playerLabel;
	private Label messageLabel;
	private Label yourTurnLabel;
	private Board board;
	private int yourcolor;
	
	private boolean yourturn = false;
	
	private List<FXPoint> selectedPoints = new ArrayList<FXPoint>();
	private Move move = null;
	GameScene thisref = this;
	
	private StandardMoveHandler movhandler;
	
	
	ClientFX parent;
	
	/**
	 * @param parent Main Class
	 * @param b @see studia.Board.Board
	 * @param ycol this client player number
	 */
	GameScene(ClientFX parent, Board b, int ycol) {
		messageLabel = new Label("");
		yourTurnLabel = new Label("");
		
		board = b;

		GUIBoardBuilder fxBoardBuilder = new GUIBoardBuilder(board, sceneWidth, sceneHeight);
		BorderPane root = new BorderPane();
		Pane workspacePane = fxBoardBuilder.buildBoard();
		
		setPointsClickedEventHandler();
		
		playerLabel = new Label("Your color " + studia.Utils.Color.colorName(ycol));
		HBox topHBox = new HBox();
		HBox bottomHBox = new HBox();
		topHBox.getChildren().add(messageLabel);
		bottomHBox.getChildren().addAll(playerLabel, yourTurnLabel);
		root.setTop(topHBox);
		root.setCenter(workspacePane);
		root.setBottom(bottomHBox);
		scene = new Scene(root, sceneWidth, sceneHeight + 80);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		yourcolor = ycol;
		movhandler = new StandardMoveHandler(board);
		//updateUI();
	}
	
	/**
	 * clears selected fields on board
	 */
	private void clearSelected() {
		if(selectedPoints.size() >= 1) {
			selectedPoints.get(0).isClicked = false;
			selectedPoints.get(0).getCircle().setStrokeWidth(1);
		}
		
		if(selectedPoints.size() >= 2) {
			selectedPoints.get(1).isClicked = false;
			selectedPoints.get(1).getCircle().setStrokeWidth(1);
		}
		selectedPoints.clear();
	}
	
	/**
	 * Translate Points into ints
	 */
	private Move decodeMove(FXPoint oldPoint, FXPoint newPoint) {
        int oldPos = -1;
        int newPos = -1;
        for (Map.Entry<Integer, Point> entry : board.validPointsMap.entrySet()) {
            if (entry.getValue() == oldPoint) oldPos = entry.getKey();
            if (entry.getValue() == newPoint) newPos = entry.getKey();
        }
        return new Move(oldPos, newPos);
	}
	
	/**
	 * Marks your destination fields with dark gray color.
	 */
	private void markFinish() {
		for(Point p: board.players[yourcolor].finishPoints)
			((FXPoint)p).updateCircle(javafx.scene.paint.Color.DARKGRAY);
	}
	
	/**
	 * Sets click event handlers for fields on board
	 */
	private void setPointsClickedEventHandler() {
		for (int i = 0; i < board.length; i++) {
			for (int j=0; j<board.height; j++) {
				if(board.points[i][j] == null) continue;
					
				if (board.points[i][j] instanceof FXPoint == false) continue;
				FXPoint point = (FXPoint) board.points[i][j];
				Circle circle = point.getCircle();
				circle.setOnMouseClicked(e -> {
					if(!yourturn) return;
					if(selectedPoints.size() == 0 && (point.pawn == null || point.pawn.color != yourcolor)) return;

				/*if (client.getYourNumber() != client.currentPlayer) {
						Platform.runLater( () -> ClientApp.messageLabel.setText("It's not your turn!"));
						return;
				}*/
					if(e.getButton() == javafx.scene.input.MouseButton.SECONDARY) {
						move = decodeMove(point, point);
						clearSelected();
						return;
					}
					if (point.isClicked == false){
						selectedPoints.add(point);
						circle.setStrokeWidth(5);
						point.isClicked = true;
						for(Point p: movhandler.getAvailableMoves(board.players[yourcolor], point))
							((FXPoint)p).updateCircle(javafx.scene.paint.Color.LIGHTGRAY);
					} else {
						board.printBoard();
						markFinish();
						selectedPoints.remove(point);
						circle.setStrokeWidth(1);
						point.isClicked = false;
					}
					if (selectedPoints.size() == 2) {
						move = decodeMove(selectedPoints.get(0), selectedPoints.get(1));
						clearSelected();
					}
				});
			}        
		}
	}
	
	/**
	 * Returns this scene
	 */
	public Scene get() { return scene; }
	
	/**
	 * @param yourturn if true "Your turn" message is shown and player can make move
	 */
	public void setMyTurn(boolean yourturn) {
		messageLabel.setText("");
		if (yourturn) {
			markFinish();
			yourTurnLabel.setText("Your turn");
			yourTurnLabel.setStyle("-fx-background-color: lightgreen;");
		} else {
			yourTurnLabel.setText("");
			yourTurnLabel.setStyle("-fx-background-color: lightyellow;");
		}
		this.yourturn = yourturn;
	}
	
	/**
	 * shows message to user
	 */
	public void showMessage(String msg) {
		messageLabel.setText(msg);
	}
	
	
	private void clearMove() {
		move = null;
	}
	
	/**
	 * Blocking call that waits for player to make move and returns that move.
	 */
	public Move getMove() {
		clearMove();
		clearSelected();
		board.printBoard();
		try {
			while(move == null) Thread.sleep(1);
		} catch(Exception e) {}
		System.out.println(move.toString());
		return move;
	}

    
}

