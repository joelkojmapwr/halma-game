package studia.Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import studia.Board.Board;
import studia.Board.GUIBoardBuilder;
import studia.MoveHandler.FXMoveHandler;
import studia.Utils.FXPoint;
import studia.Common.FXGame;




public class ClientApp extends Application {

	private int sceneWidth = 800;
	private int sceneHeight = 800;
	public static FXClient client;
    public FXGame game;
	public GUIBoardBuilder fxBoardBuilder;
	public static Label currentPlayerLabel;
	public static Label messageLabel;
	public static Label yourTurnLabel;


	@Override
    public void start(Stage primaryStage) {
		messageLabel = new Label("");
		yourTurnLabel = new Label("");
		
        this.game = client.game;

		Board board = game.getBoard();

		fxBoardBuilder = new GUIBoardBuilder(board, sceneWidth, sceneHeight);
		BorderPane root = new BorderPane();
		Pane workspacePane = fxBoardBuilder.buildBoard();
		
		
		Label playerLabel = new Label("Your color " + studia.Utils.Color.colorName(client.yournumber));
		currentPlayerLabel = new Label("Player " + studia.Utils.Color.colorName(game.curplr) + " turn");
		HBox topHBox = new HBox();
		HBox bottomHBox = new HBox();
		topHBox.getChildren().add(messageLabel);
		FXMoveHandler moveHandler = new FXMoveHandler(board, client);
		// need to add also label where msg from server will be displayed
		bottomHBox.getChildren().addAll(playerLabel, yourTurnLabel, currentPlayerLabel);
		//bottomPane.setValignment(playerLabel, javafx.geometry.VPos.CENTER);
		root.setTop(topHBox);
		root.setCenter(workspacePane);
		root.setBottom(bottomHBox);
		Scene scene = new Scene(root, sceneWidth, sceneHeight + 80);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		updateUI();

		primaryStage.setScene(scene);
		primaryStage.setTitle("Client");
		primaryStage.show();

	}

	public static void updateUI() {
		currentPlayerLabel.setText("Player " + studia.Utils.Color.colorName(client.currentPlayer) + " turn");
		messageLabel.setText("");
		if (client.getYourNumber() == client.currentPlayer) {
			yourTurnLabel.setText("Your turn");
			yourTurnLabel.setStyle("-fx-background-color: lightgreen;");
		} else {
			yourTurnLabel.setText("");
			yourTurnLabel.setStyle("-fx-background-color: lightyellow;");
		}
	}

	public static void launchApp(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

