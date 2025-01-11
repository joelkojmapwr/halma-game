package studia.Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import studia.Board.Board;
import studia.Board.FXBoardBuilder;
import studia.MoveHandler.FXMoveHandler;
import studia.Utils.FXPoint;
import studia.Common.FXGame;


public class ClientApp extends Application {

	private int sceneWidth = 800;
	private int sceneHeight = 800;
	public static FXClient client;
    public FXGame game;
	public FXBoardBuilder fxBoardBuilder;


	@Override
    public void start(Stage primaryStage) {
		
        this.game = client.game;

		Board board = game.getBoard();
		FXPoint point = (FXPoint) board.validPointsMap.get(0);
		System.out.println(point.getCircle().getRadius());

		fxBoardBuilder = new FXBoardBuilder(board, sceneWidth, sceneHeight);
		Pane root = fxBoardBuilder.buildBoard();
		FXMoveHandler moveHandler = new FXMoveHandler(board, client);

		Scene scene = new Scene(root, sceneWidth, sceneHeight);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.show();

	}

	public static void launchApp(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

